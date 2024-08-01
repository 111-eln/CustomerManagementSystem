package com.atmosware.customerManagementSystem.business.concretes;

import com.atmosware.customerManagementSystem.business.abstracts.MernisService;
import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Service
@AllArgsConstructor
public class MernisManager implements MernisService {
    @Override
    public boolean CheckIfRealPerson(CreateCustomerRequest customer) {

        try {
            String url = "https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx?op=TCKimlikNoDogrula";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();//bu sınıfı kullanarak sunuculara post,put gibi http istekleri gönderilebilir ve sonuc alınabilir
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
            String xml = String.format("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Body>\n" +
                    "    <TCKimlikNoDogrula xmlns=\"http://tckimlik.nvi.gov.tr/WS\">\n" +
                    "      <TCKimlikNo>%s</TCKimlikNo>\n" +
                    "      <Ad>%s</Ad>\n" +
                    "      <Soyad>%s</Soyad>\n" +
                    "      <DogumYili>%s</DogumYili>\n" +
                    "    </TCKimlikNoDogrula>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>", Long.parseLong(customer.getCitizenNumber()), customer.getName(), customer.getSurName(), customer.getBirth_Date());
            con.setDoOutput(true);//bağlantının veri gönderip gönderemeyeceği ayarlanır.
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());//çıktı akışına gönderilcek veriler yazılır
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();
            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();
            System.out.println(response.toString());
            response.toString().substring(306, 310);
            if (response.toString().substring(306, 310).equals("true")) {

                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;

        }


    }







}
