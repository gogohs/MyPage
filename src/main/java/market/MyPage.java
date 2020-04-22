package market;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MyPage_table")
public class MyPage {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Integer productId;
        private Integer reservationId;
        private Integer paymentId;
        private String productName;
        private String reservationStatus;
        private String paymentStatus;

        
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
        public Integer getProductId() {
            return productId;
        }
    
        public void setProductId(Integer productId) {
            this.productId = productId;
        }
        public Integer getReservationId() {
            return reservationId;
        }
    
        public void setReservationId(Integer reservationId) {
            this.reservationId = reservationId;
        }
        public Integer getPaymentId() {
            return paymentId;
        }
    
        public void setPaymentId(Integer paymentId) {
            this.paymentId = paymentId;
        }
        public String getProductName() {
            return productName;
        }
    
        public void setProductName(String productName) {
            this.productName = productName;
        }
        public String getReservationStatus() {
            return reservationStatus;
        }
    
        public void setReservationStatus(String reservationStatus) {
            this.reservationStatus = reservationStatus;
        }
        public String getPaymentStatus() {
            return paymentStatus;
        }
    
        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
      
}
