package dbcache.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class Tikun {
    String id1;
    String number;
    LocalDate date;
    String month;
    String department;
    String repNumber;
    String model;
    String IMEI;
    String income;
    String warranty;
    String technician;
    String mins;
    String repaired;
    String comm;
}
