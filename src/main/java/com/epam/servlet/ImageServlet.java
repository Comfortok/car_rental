package com.epam.servlet;

import com.epam.action.UpdateCarImageAction;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
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
        HttpSession session = request.getSession();
        ServletContext app = getServletContext();
        String uploadPath = app.getRealPath(APP_PATH_SLASH);
        String uploadTargetPath = app.getRealPath(APP_PATH_SLASH).concat(IMAGE_CAR_FOLDER);
        uploadPath = uploadPath.replace(APP_PATH_REPLACE_PART, EMPTY_STRING);
        uploadPath = uploadPath.concat(IMAGE_FOLDER);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            String fileName;
            String filePath;
            String fileTargetPath;
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        filePath = uploadPath + File.separator + fileName;
                        fileTargetPath = uploadTargetPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        File storeFile2 = new File(fileTargetPath);
                        item.write(storeFile);
                        session.setAttribute(CAR_IMAGE, fileName);
                        Files.copy(storeFile.toPath(), storeFile2.toPath());
                    }
                }

            }
        } catch (Exception e) {
            LOG.error("Exception in ImageServlet has happened. Can not write an image. ", e);
        }
        UpdateCarImageAction action = new UpdateCarImageAction();
        String forward = action.execute(request, response);
        getServletContext().getRequestDispatcher(forward).forward(request, response);
    }
}