package com.emi.roots.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;


public class HttpOperation {


    /**
     * 上传图片
     *
     * @param urlStr
     * @param textMap
     * @param fileMap
     * @return
     */
    public static String formUpload(String urlStr, Map<String, Object> textMap,
                                    Map<String, String> fileMap) {
        String res = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            conn.connect();
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // text
            if (textMap != null) {
                StringBuffer strBuf = new StringBuffer();
                Iterator<Map.Entry<String, Object>> iter = textMap.entrySet()
                        .iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, Object> entry = iter.next();
                    String inputName = (String) entry.getKey();
                    Object inputValue = entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    strBuf.append("\r\n").append("--").append(BOUNDARY)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"\r\n\r\n");
                    strBuf.append(inputValue);
                }
                out.write(strBuf.toString().getBytes());
            }

            // file
            if (fileMap != null) {
                Iterator<Map.Entry<String, String>> iter = fileMap.entrySet()
                        .iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String inputName = (String) entry.getKey();
                    String inputValue = (String) entry.getValue();
                    if (inputValue == null) {
                        continue;
                    }
                    File file = new File(inputValue);
                    String filename = file.getName();
                    // MagicMatch match = Magic.getMagicMatch(file, false,
                    // true);
                    String contentType = "application/octet-stream";

                    StringBuffer strBuf = new StringBuffer();
                    strBuf.append("\r\n").append("--").append(BOUNDARY)
                            .append("\r\n");
                    strBuf.append("Content-Disposition: form-data; name=\""
                            + inputName + "\"; filename=\"" + filename
                            + "\"\r\n");
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

                    out.write(strBuf.toString().getBytes());

                    DataInputStream in = new DataInputStream(
                            new FileInputStream(file));
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                    in.close();
                }
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = conn.getInputStream();
                // 读取返回数据
                StringBuffer strBuf = new StringBuffer();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    strBuf.append(line).append("\n");
                }
                res = strBuf.toString();
                reader.close();
                reader = null;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + urlStr);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    /**
     * 表单Post提交
     * 提交数据的Url
     *
     * @return webService返回的Json串，失败返回null
     * @throws Exception
     */
    public static String postRequest(String urlStr,
                                     Map<String, Object> rawParams) {
        StringBuilder builder = null;
        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            // conn.setRequestProperty("Content-Type", "text/html");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            conn.connect();
            if (rawParams != null) {
                String str = "";
                for (String key : rawParams.keySet()) {
                    str = str + key + "=" + rawParams.get(key) + "&";
                }
                str = str.substring(0, str.length() - 1);
                os = conn.getOutputStream();
                os.write(str.getBytes());
                os.close();
            }
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                Reader in = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(in);
                builder = new StringBuilder();
                String resultTemp;
                while ((resultTemp = reader.readLine()) != null) {
                    builder.append(resultTemp);
                }
                is.close();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder == null ? null : builder.toString();
    }

    /**
     * 发送post请求
     *
     * @param urlString 后台地址
     * @param pJson     Json串
     * @return
     */

    public static String postRequest(String urlString, String pJson) {

        URL url;
        StringBuilder builder = null;
        OutputStream os = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/html");
            conn.connect();
            if (pJson == null) {
                pJson = "";
            }
            os = conn.getOutputStream();
            os.write(pJson.getBytes());
            os.close();
//            Log.d("jinxn", urlString == null ? "" : urlString);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { //
                // 获取响应码
//                Log.i("响应码", conn.getResponseCode() + "");
                is = conn.getInputStream();
                Reader in = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(in);
                builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                is.close();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.d("jinxn", builder == null ? "" : builder.toString());
        return builder == null ? null : builder.toString();
    }

    /**
     * 发送Get请求
     *
     * @return 网络服务器返回
     */

    public static String getRequest(String urlString) {

        URL url;
        StringBuilder builder = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/html");
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { //
                // 获取响应码
                is = conn.getInputStream();
                Reader in = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(in);
                builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                is.close();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder == null ? null : builder.toString();
    }

//	/**
//	 * 文件上传
//	 *
//	 * @param url
//	 *            网络地址
//	 * @param f
//	 *            文件
//	 * @param gid
//	 * @param type
//	 * @return
//	 */
//	public static boolean postFile(String url, File f, String gid, String type) {
//		if (url == null || f == null) {
//			System.out.println("URL或者文件为NULL");
//			return false;
//		}
//		url = url + "?gid=" + gid + "&type=" + type;
//		HttpClient client = new DefaultHttpClient();
//		HttpPost httpPost = new HttpPost(url);
//		HttpResponse response = null;
//		try {
//			FileEntity entity = new FileEntity(f, "binary/octet-stream");
//			httpPost.setEntity(entity);
//			response = client.execute(httpPost);
//		} catch (Exception e) {
//			Log.v("HenryLog", e.toString());
//			e.printStackTrace();
//		} finally {
//			if (client != null) {
//				client.getConnectionManager().shutdown();
//			}
//		}
//
//		// 判断上传的状态和打印调试信息
//		if (response != null
//				&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//			// 打印调试信息,上传的url和上传的文件大小
//			System.out.println(MessageFormat.format(
//					"upload picture success! url = [{0}],file size = [{1}]",
//					url, f.length()));
//			return true;
//		} else {
//			// System.out.println(response.getStatusLine().getStatusCode());
//		}
//		return false;
//	}
//

    /**
     * 文件上传
     * 网络地址
     *
     * @param f 文件
     * @return
     */
    public static String postFile(String urlString, File f) {
        URL url;
        StringBuilder builder = null;
        try {
            url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            // 获取响应码
            OutputStream os = conn.getOutputStream();
            FileInputStream in = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            in.close();
            os.close();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { //
                InputStream is = conn.getInputStream();
                Reader inReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inReader);
                builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                is.close();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder == null ? null : builder.toString();
    }
//
//	/**
//	 * 后台下载图片文件
//	 *
//	 * @param url
//	 *            访问后台地址
//	 * @param gid
//	 *            关联的gid
//	 * @param type
//	 *            关联的类型
//	 * @return 0:后台返回错误，1：返回文件成功，2：后台文件不存在
//	 */
//	public static int downloadFile(String url, String gid, String type) {
//
//		if (url == null) {
//			return 0;
//		}
//		url = url + "?gid=" + gid + "&type=" + type;
//		HttpClient client = new DefaultHttpClient();
//		HttpPost httpPost = new HttpPost(url);
//		HttpResponse httpResponse = null;
//		try {
//			httpResponse = client.execute(httpPost);
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		} finally {
//			if (client != null) {
//				client.getConnectionManager().shutdown();
//			}
//		}
//		if (httpResponse != null
//				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//			try {
//				HttpEntity entity = httpResponse.getEntity();
//				long length = entity.getContentLength();
//				if (length == 0) {
//					return 2;
//				}
//				InputStream is = entity.getContent();
//				FileOutputStream fileOutputStream = null;
//				if (is != null) {
//
//					File file = new File(
//							Environment.getExternalStorageDirectory(), type
//									+ ".png");
//					fileOutputStream = new FileOutputStream(file);
//					byte[] buf = new byte[1024];
//					int ch = -1;
//					// long count=0;
//					while ((ch = is.read(buf)) != -1) {
//						fileOutputStream.write(buf, 0, ch);
//						// count += ch;
//						// Log.v("HenryLog", "count"+count);
//						// if (length > 0) {
//						// int _Curr = (int) (((double) count / length) * 100);
//						// }
//					}
//
//				}
//				fileOutputStream.flush();
//				if (fileOutputStream != null) {
//					fileOutputStream.close();
//				}
//				return 1;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 0;
//	}

    public static void  main(String[] strs){
        String url="https://a1.easemob.com/henrychu/testchat/users";
        String params="{\"username\":\"service26\",\"password\":\"123456\"}";
        String res=postRequest(url,params);
        System.out.println(res);
    }


}
