package petstore.endpoints;

public class Config {
  final static String BASE_URI = "http://petstore.swagger.io/v2";
  final static String CREATE_PET = "pet";
  final static String GET_PET_BY_ID = "pet/{petId}";
  final static String GET_PET_BY_STATUS = "pet/findByStatus";
  final static String UPDATE_PET_BY_ID = "pet";
  final static String GET_INVENTORIES_BY_STATUS = "/store/inventory";
  final static String CREATE_ORDER = "/store/order";
  final static String FIND_ORDER_BY_ID = "/store/order/{orderId}";
}
