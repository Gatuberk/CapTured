package co.edu.ue.dto;

import co.edu.ue.model.Order.Status;
import co.edu.ue.model.Orderdetail;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements IOrderDto {

  private int id;
  private int userId;
  private Status status;
  private Date orderDate;
  private double totalPrice;
  private List<Orderdetail> details;
}
