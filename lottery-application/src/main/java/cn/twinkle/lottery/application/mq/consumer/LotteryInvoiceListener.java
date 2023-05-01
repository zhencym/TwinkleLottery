package cn.twinkle.lottery.application.mq.consumer;

import java.util.Optional;
import javax.annotation.Resource;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Author: zhencym
 * @DATE: 2023/4/30
 * 消息消费者
 */
@Component
public class KafkaConsumer {

  private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  @Resource
  private DistributionGoodsFactory distributionGoodsFactory;

  @KafkaListener(topics = "KafkaProducer.TOPIC_TEST", groupId = "KafkaProducer.TOPIC_GROUP")
  public void topicTest(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    Optional<?> message = Optional.ofNullable(record.value());
    if (message.isPresent()) {
      Object msg = message.get();
      logger.info("topic_test 消费了： Topic:" + topic + ",Message:" + msg);
      // 手动ack
      ack.acknowledge();
    }
  }

}
