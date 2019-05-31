package petstore.models;

public class OrderModel {

  private int Id;
  private int petId;
  private int quantity;
  private String shipDate;
  private String status;
  private boolean complete;

  public OrderModel(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
    Id = id;
    this.petId = petId;
    this.quantity = quantity;
    this.shipDate = shipDate;
    this.status = status;
    this.complete = complete;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getPetId() {
    return petId;
  }

  public void setPetId(int petId) {
    this.petId = petId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getShipDate() {
    return shipDate;
  }

  public void setShipDate(String shipDate) {
    this.shipDate = shipDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }
}
