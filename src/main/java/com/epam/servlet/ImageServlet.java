package com.epam.servlet;

import com.epam.action.AddCarAction;
import com.epam.action.ShowAllCarsAction;
import com.epam.action.UpdateCarImageAction;
import com.epam.util.Validator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

import static com.epam.constant.ConstantField.*;
import static com.epam.constant.JspPagePath.ERROR_PAGE;

public class ImageServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(UpdateCarImageAction.class);
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty(JAVA_TEMP_DIR)));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        ServletContext app = getServletContext();
        String uploadPath = app.getRealPath(APP_PATH_SLASH);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String command = null;
        String fileName = null;
        String filePath;
        try {
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        filePath = uploadPath + IMAGE_CAR_FOLDER + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        request.setAttribute(CAR_IMAGE, fileName);
                    } else {
                        String name = item.getFieldName();
                        String value = item.getString();
                        request.setAttribute(name, value);
                        if (name.equalsIgnoreCase(COMMAND)) {
                            command = value;
                        }
                    }
                }
            }
            if (!Validator.checkImageFormat(fileName)) {
                request.setAttribute(ERROR_FORMAT, ERROR_FORMAT_MESSAGE);
                ShowAllCarsAction action = new ShowAllCarsAction();
                String forward = action.execute(request, response);
                getServletContext().getRequestDispatcher(forward).forward(request, response);
                return;
            }
        } catch (Exception e) {
            LOG.error("Exception in ImageServlet has happened. Can not write an image. ", e);
            getServletContext().getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }
        String forward;
        if (command != null) {
            AddCarAction action = new AddCarAction();
            forward = action.execute(request, response);
            getServletContext().getRequestDispatcher(forward).forward(request, response);
        } else {
            UpdateCarImageAction action = new UpdateCarImageAction();
            forward = action.execute(request, response);
            getServletContext().getRequestDispatcher(forward).forward(request, response);
        }
    }
}