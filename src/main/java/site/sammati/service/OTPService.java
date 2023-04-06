package site.sammati.service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class OTPService {

    public static Integer generateOTP()
    {
        Random random = new Random();
        int OTP = (100000 + random.nextInt(900000));

        if(OTP%2==0)
            OTP+=1;

        return OTP;
    }

    public static Integer sendOTP(String phno, String message) {
        //Third party API call
        int code=-1;
        try
        {
            String apiKey="Tf0l5vJFUNs3EqRDwmVpjkIHLbYXSBtxGicuhyPQdW9nr2e178brnxh0MUt2DmlTGoPKJLEvaAYN9SW6";
            String sendId="FSTSMS";
            message= URLEncoder.encode(message, "UTF-8");
            String language="english";
            String route="p";
            String myUrl="https://www.fast2sms.com/dev/bulk?authorization="+apiKey+"&sender_id="+sendId+"&message="+message+"&language="+language+"&route="+route+"&numbers="+phno;

            URL url=new URL(myUrl);
            HttpsURLConnection con=(HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");
            code=con.getResponseCode();
            StringBuffer response=new StringBuffer();
            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            while(true)
            {
                String line=br.readLine();
                if(line==null)
                {
                    break;
                }
                response.append(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
}
