package cn.todo.utils.adapters.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatXmlAdapter extends XmlAdapter<String, Date> {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @Override
    public Date unmarshal(String s) throws Exception {
        return sdf.parse(s);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return sdf.format(date);
    }
}
