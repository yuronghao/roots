package com.emi.roots.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * Created by 赵永飞 on 2017-07-27.
 */
public class ImageUtil {

    public static  final int width=150;
    public static  final int height=150;

    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist, Float rate) {
        try {
            File srcfile=new File(imgsrc);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results=getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist=(int) (results[0] * rate);
                    heightdist=(int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src= ImageIO.read(srcfile);
            BufferedImage tag=new BufferedImage((int) widthdist,
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream out=new FileOutputStream(imgdist);
            JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean compressImg(String srcfilepath, String imgdist) {
        FileOutputStream out=null;
        boolean flag=false;
        try {
            int[] array=prossWidthHeight(srcfilepath);
            if(array!=null||array.length==2){
                File srcfile=new File(srcfilepath);
                int widthdist=array[0];
                int heightdist=array[1];
                // 检查文件是否存在
                if (!srcfile.exists()) {
                    flag=false;
                }
                // 开始读取文件并进行压缩
                Image src= ImageIO.read(srcfile);
                BufferedImage tag=new BufferedImage((int) widthdist,
                        (int) heightdist, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(
                        src.getScaledInstance(widthdist, heightdist,
                                Image.SCALE_SMOOTH), 0, 0, null);
                out=new FileOutputStream(imgdist);
               // JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
                String suffix = imgdist.substring(imgdist.lastIndexOf(".") + 1);
                //encoder.encode(tag);
                ImageIO.write(tag, suffix, out);
                flag=true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return flag;
        }
    }
    public static int[] prossWidthHeight(String filepath) {
        InputStream is = null;
        BufferedImage src = null;
        int tmpwidth=0;
        int tmpheight=0;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(new File(filepath));
            src = ImageIO.read(is);
            tmpwidth = src.getWidth(null); // 得到源图宽
            tmpheight = src.getHeight(null); // 得到源图高
            result[0]=tmpwidth;
            result[1]=tmpheight;
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(tmpheight>0&&tmpwidth>0){
            if(tmpheight<=150||tmpwidth<150){
                return result;
            }else{
                if(tmpwidth>tmpheight){
                    result[1]=height;
                    result[0]=150*tmpwidth/tmpheight;
                }else{
                    result[0]=width;
                    result[1]=150*tmpheight/tmpwidth;

                }
            }
        }
        return result;
    }
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * d://3.jpg 源图片
         * d://31.jpg 目标图片
         * 压缩宽度和高度都是1000
         *
         */
        System.out.println("压缩图片开始...");
        File srcfile = new File("d:/app/test.jpg");
        System.out.println("压缩前srcfile size:" + srcfile.length());
        float f=0.1f;
       // reduceImg("d:/app/test.jpg", "d:/app/31.jpg", 200, 200,f);
        //int[] array=prossWidthHeight(new File("d:/app/test.jpg"));
        compressImg("d:/app/test.jpg", "d:/app/31.jpg");
        File distfile = new File("d:/app/31.jpg");
        System.out.println("压缩后distfile size:" + distfile.length());

    }

    public static String getFileTypeByByte(byte[] b ) {
      /*  byte[] b = new byte[4];
        try {
            is.read(b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }*/
        String type = bytesToHexString(b).toUpperCase();
        if(mFileTypes.get(type)!=null){
            return mFileTypes.get(type);
        }
        return "";
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder strBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String strhs = Integer.toHexString(v);
            if (strhs.length() < 2) {
                strBuilder.append(0);
            }
            strBuilder.append(strhs);
        }
        return strBuilder.toString();
    }

    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
    static {
        // images
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("FFD8FFE0", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("49492A00", "tif");
        mFileTypes.put("424D", "bmp");
        //
        mFileTypes.put("41433130", "dwg"); // CAD
        mFileTypes.put("38425053", "psd");
        mFileTypes.put("7B5C727466", "rtf"); // 日记本
        mFileTypes.put("3C3F786D6C", "xml");
        mFileTypes.put("68746D6C3E", "html");
        mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
        mFileTypes.put("D0CF11E0", "doc");
        mFileTypes.put("D0CF11E0", "xls");//excel2003版本文件
        mFileTypes.put("5374616E64617264204A", "mdb");
        mFileTypes.put("252150532D41646F6265", "ps");
        mFileTypes.put("255044462D312E", "pdf");
        mFileTypes.put("504B0304", "docx");
        mFileTypes.put("504B0304", "xlsx");//excel2007以上版本文件
        mFileTypes.put("52617221", "rar");
        mFileTypes.put("57415645", "wav");
        mFileTypes.put("41564920", "avi");
        mFileTypes.put("2E524D46", "rm");
        mFileTypes.put("000001BA", "mpg");
        mFileTypes.put("000001B3", "mpg");
        mFileTypes.put("6D6F6F76", "mov");
        mFileTypes.put("3026B2758E66CF11", "asf");
        mFileTypes.put("4D546864", "mid");
        mFileTypes.put("1F8B08", "gz");
    }
}
