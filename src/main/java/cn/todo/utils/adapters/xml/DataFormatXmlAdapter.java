package cn.todo.utils.adapters.xml;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatXmlAdapter extends XmlAdapter<String, Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public Date unmarshal(String v) throws Exception {
        return sdf.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return sdf.format(v);
    }
}

