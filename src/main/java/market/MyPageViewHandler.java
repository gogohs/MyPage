package market;

import market.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_CREATE_1 (@Payload Reserved reserved) {
        try {
            if (reserved.isMe()) {
                // view 객체 생성
                MyPage myPage = new MyPage();
                // view 객체에 이벤트의 Value 를 set 함
                myPage.setProductId(reserved.getProductId());
                myPage.setReservationId(Integer.parseInt(reserved.getId().toString()));
                myPage.setProductName(reserved.getProductName());
                myPage.setReservationStatus(reserved.getReservationStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCanceled_then_UPDATE_1(@Payload ReservationCanceled reservationCanceled) {
        try {
            if (reservationCanceled.isMe()) {
                // view 객체 조회
                List<MyPage> myPageList = myPageRepository.findByReservationId(Integer.parseInt(reservationCanceled.getId().toString()));
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setReservationStatus(reservationCanceled.getReservationStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_2(@Payload Paid paid) {

        try {
            if (paid.isMe()) {
                // view 객체 조회

                List<MyPage> myPageList = myPageRepository.findByReservationId(paid.getReservationId());
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setPaymentStatus(paid.getPaymentStatus());
                    myPage.setPaymentId(paid.getId());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
                myPageList = myPageRepository.findByPaymentId(Integer.parseInt(paid.getId().toString()));
                for(MyPage myPage : myPageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    myPage.setPaymentStatus(paid.getPaymentStatus());
                    // view 레파지 토리에 save
                    myPageRepository.save(myPage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}