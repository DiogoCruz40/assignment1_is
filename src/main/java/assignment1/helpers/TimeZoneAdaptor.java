package assignment1.helpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneAdaptor extends XmlAdapter<String, LocalDateTime> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        LocalDateTime parse = LocalDateTime.parse(v, dateTimeFormatter);
        return parse;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return dateTimeFormatter.format(v);
    }
}