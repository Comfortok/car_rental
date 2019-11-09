package com.epam.action;

public class ConstantField {
    //AddCarAction
    public static final String CAR_REGISTERED_NUMBER = "regNumber";
    public static final String CAR_BRAND = "brand";
    public static final String CAR_MODEL = "model";
    public static final String CAR_COLOR = "color";
    public static final String CAR_CATEGORY = "category";
    public static final String CAR_TRANSMISSION = "transmission";
    public static final String CAR_BODY = "body";
    public static final String CAR_ENGINE = "engine";
    public static final String CAR_AC = "airConditioner";
    public static final String CAR_AVAILABILITY = "available";
    public static final String CAR_ENGINE_VOLUME = "engineVolume";
    public static final String CAR_BAGGAGE_AMOUNT = "baggage";
    public static final String CAR_SEAT_AMOUNT = "seat";
    public static final String CAR_FUEL_CONSUMPTION = "fuelConsumption";
    public static final String CAR_DOOR_AMOUNT = "door";
    public static final String CAR_PRODUCTION_YEAR = "year";
    public static final String CAR_IMAGE = "image";
    public static final String CAR_IMAGE_FOLDER = "img/car/";
    public static final String CAR_IMAGE_FORMAT = ".jpg";

    //AddCarForm
    public static final String BRAND_LIST = "brandList";
    public static final String MODEL_LIST = "modelList";
    public static final String COLOR_LIST = "colorList";
    public static final String CATEGORY_LIST = "categoryList";
    public static final String TRANSMISSION_LIST = "transmissionList";
    public static final String BODY_LIST = "bodyList";
    public static final String ENGINE_LIST = "engineList";

    //ChangeCarImage
    public static final String CAR_ID = "carId";

    //ConfirmOrder
    public static final String ORDER_ID = "orderId";

    //EmptyAction
    public static final String EMPTY_ACTION_MESSAGE = "No such action";
    public static final String EMPTY_ACTION_ERROR = "emptyError";

    //LanguageAction
    public static final String COMMAND = "command";
    public static final String ENGLISH = "EN";
    public static final String RUSSIAN = "RU";
    public static final String LOCALE_ATTRIBUTE = "locale";

    //LoginAction
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String EMPTY_FIELD_ERROR_MESSAGE = "Some fields are empty";
    public static final String EMPTY_FIELD_ERROR = "errorEmpty";
    public static final String NO_SUCH_USER_ERROR_MESSAGE = "No such email or password";
    public static final String NO_SUCH_USER_ERROR = "errorLoginData";
    public static final String USER_ATTRIBUTE = "user";
    public static final String USER_ROLE_ATTRIBUTE = "roleId";
    public static final String USER_ID = "userId";

    //OrderCarAction
    public static final String ORDER_PAYMENT_SUM = "payment";
    public static final String ORDER_START_DATE = "startDate";
    public static final String ORDER_END_DATE = "endDate";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DRIVER_NAME = "driverName";
    public static final String DRIVER_SURNAME = "driverSurname";
    public static final String DRIVER_BIRTH_DATE = "driverBirth";
    public static final String DRIVER_PHONE = "driverPhone";
    public static final String PASSPORT_NUMBER = "passportNumber";
    public static final String PASSPORT_ISSUE_DATE = "passportIssue";
    public static final String PASSPORT_EXPIRY_DATE = "passportExpiry";
    public static final String PASSPORT_AUTHORITY = "passportAuthority";
    public static final String LICENCE_NUMBER = "licenceNumber";
    public static final String LICENCE_ISSUE_DATE = "licenceIssue";
    public static final String LICENCE_EXPIRY_DATE = "licenceExpiry";
    public static final String LICENCE_AUTHORITY = "licenceAuthority";
    public static final String LICENCE_CATEGORY = "licenceCategory";
    public static final String CAR_PRICE = "carPrice";

    //OrderFormAction
    public static final String ORDER_PERIOD = "period";

    //OrderPaymentAction
    public static final String PAYMENT_SUM = "paymentSum";
    public static final String ACTUAL_SUM = "actualSum";
    public static final String WRONG_SUM_ERROR_MESSAGE = "Payment sum can't be less";
    public static final String WRONG_SUM_ERROR = "wrongSum";

    //orderStatuses and paymentStatuses
    public static final int FORMED_ORDER_STATUS_ID = 1;
    public static final int CONFIRMED_ORDER_STATUS_ID = 2;
    public static final int PAID_ORDER_STATUS_ID = 3;
    public static final int ARCHIVED_ORDER_STATUS_ID = 4;
    public static final int DECLINED_ORDER_STATUS_ID = 5;
    public static final int PAYMENT_BANK_CARD_ID = 1;
    public static final int PAYMENT_MONEY_TRANSFER_ID = 2;
    public static final int PAYMENT_PAID_ID = 1;
    public static final int PAYMENT_NOT_PAID_ID = 0;
    public static final String STATUS_ID = "statusId";

    //RegisterAction
    public static final String USER_CONFIRM_PASSWORD = "passwordRepeat";
    public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "Please, confirm password correctly";
    public static final String CONFIRM_PASSWORD_ERROR = "errorConfirm";
    public static final String VALIDATION_ERROR_MESSAGE = "Incorrect email or password";
    public static final String VALIDATION_ERROR = "errorValidation";
    public static final String EMAIL_ERROR_MESSAGE = "Email is already exist";
    public static final String EMAIL_ERROR = "errorEmail";

    //ShowAllAdminOrdersAction
    public static final String ORDER_LIST = "orders";

    //ShowAllCarsAction
    public static final String ALL_CARS_LIST = "availableCars";
    public static final String CAR_AVAILABLE_YES = "yes";

    //CarDAO
    public static final String CAR_TRANSMISSION_NAME = "transmission_name";
    public static final String PRODUCTION_YEAR = "production_year";
    public static final String ENGINE_TYPE_NAME = "engine_type_name";
    public static final String ENGINE_NAME = "engine_name";
    public static final String REGISTERED_NUMBER = "registered_number";
    public static final String BRAND_ID = "brand_id";
    public static final String BRAND_NAME = "brand_name";
    public static final String MODEL_ID = "model_id";
    public static final String MODEL_NAME = "model_name";
    public static final String COLOR_ID = "color_id";
    public static final String COLOR_NAME = "color_name";
    public static final String CAR_CATEGORY_ID = "car_category_id";
    public static final String CAR_CATEGORY_NAME = "car_category_name";
    public static final String CAR_PRICE_PER_DAY = "price_per_day";
    public static final String CAR_TRANSMISSION_ID = "transmission_id";
    public static final String BODY_ID = "body_id";
    public static final String BODY_NAME = "body_name";
    public static final String ENGINE_TYPE_ID = "engine_type_id";
    public static final String HAS_AC = "has_air_conditioner_yn";
    public static final String IS_AVAILABLE = "is_available_yn";
    public static final String ENGINE_VOLUME = "engine_volume";
    public static final String BAGGAGE_AMOUNT = "baggage_amount";
    public static final String PASSENGER_AMOUNT = "passenger_amount";
    public static final String FUEL_CONSUMPTION = "fuel_consumption";
    public static final String DOOR_AMOUNT = "door_amount";
    public static final String MILEAGE = "mileage";
    public static final String CAR_MILEAGE = "carMileage";
    public static final String CAR_NUMBER = "carNumber";

    //driverDAO
    public static final String DRIVER_ID = "driver_id";

    //orderDAO
    public static final String RESULTSET_ORDER_ID = "order_id";
    public static final String RESULTSET_USER_ID = "user_id";
    public static final String RESULTSET_CAR_ID = "car_id";
    public static final String RESULTSET_STATUS_ID = "status_id";
    public static final String RESULTSET_STATUS_NAME = "status_name";
    public static final String RESULTSET_START_DATE = "start_date";
    public static final String RESULTSET_END_DATE = "end_date";
    public static final String RESULTSET_PAYMENT_SUM = "payment_sum";
    public static final String RESULTSET_DRIVER_NAME = "name";
    public static final String RESULTSET_DRIVER_SURNAME = "surname";
    public static final String RESULTSET_DRIVER_BIRTH_DATE = "date_of_birth";
    public static final String RESULTSET_DRIVER_PHONE_NUMBER = "phone_number";
    public static final String RESULTSET_DRIVER_PASSPORT_NUMBER = "passport_number";
    public static final String RESULTSET_PASSPORT_ISSUE_DATE = "passport_issue";
    public static final String RESULTSET_PASSPORT_EXPIRY_DATE = "passport_expiry";
    public static final String RESULTSET_PASSPORT_AUTHORITY = "passport_authority";
    public static final String RESULTSET_LICENCE_NUMBER = "licence_number";
    public static final String RESULTSET_LICENCE_ISSUE_DATE = "licence_issue";
    public static final String RESULTSET_LICENCE_EXPIRY_DATE = "licence_expiry";
    public static final String RESULTSET_LICENCE_AUTHORITY = "licence_authority";
    public static final String RESULTSET_LICENCE_CATEGORY = "category";

    //UserDAO
    public static final String RESULTSET_ROLE_ID = "role_id";

    //Servlet
    public static final String ERROR_MESSAGE = "errorMessage";
}