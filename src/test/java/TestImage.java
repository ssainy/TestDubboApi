import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestImage {

    public static void main(String[] args) throws Exception
    {
        //先模拟一个图形byte[]
        byte[] b1 =image2Bytes("d:\\1.jpg");
        //存为文件
        buff2Image(b1,"d:\\test.jpg");

        System.out.println("Hello World!");
    }

    static void buff2Image(byte[] b,String tagSrc) throws Exception
    {
        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }

    static byte[] image2Bytes(String imgSrc) throws Exception
    {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();
        return bytes;
    }

}  