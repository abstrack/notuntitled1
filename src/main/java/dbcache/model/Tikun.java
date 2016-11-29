package dbcache.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@RequiredArgsConstructor()
public class Tikun {
    @NonNull
    String number;
    LocalDate date;
    String month;
    String department;
    String repNumber;
    String model;
    String IMEI;
    String income;
    String warranty;
    String comm;
    String technician;
    String mins;
    String repaired;
}
