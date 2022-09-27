import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public  static  boolean isName(String value){
        Pattern pattern = Pattern.compile(
                "^([a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]\\s{0,1}){7,25}$"
        );
        Matcher matcher = pattern.matcher(value);
        return  matcher.matches(); // nối kiểm tra regex
    }
    public static  boolean isDate(String value){
        Pattern pattern = Pattern.compile("^(\\d{2}\\/){2}\\d{4}$");
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches()){
            SimpleDateFormat dateFormat  = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                Date javaDate = dateFormat.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
                return  false;
            }
        }else {
            return false;
        }
        return true;
    }
    public static boolean isCountry(String value){
        Pattern pattern = Pattern.compile(".{1,500}");
        Matcher matcher = pattern.matcher(value);
        return  matcher.matches();
    }

}
