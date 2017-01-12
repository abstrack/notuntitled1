package dbcache.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import dbcache.model.Tikun;
import dbcache.repo.TikunRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

public class Quickstart {
    /**
     * Application name.
     */
    public static final String ACCOUNT_SID = "AC4bffcfe6e62709f1853992755ad18f19";
    public static final String AUTH_TOKEN = "5263154a2f4a79407b58208c34a0da1b";
    private static final String APPLICATION_NAME =
            "Google Sheets API Java Quickstart";

    public static TikunRepo tikunRepo;
    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     * <th>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws Exception {
        // Load client secrets.
        InputStream in =
                Quickstart.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     *
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws Exception {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static List<Tikun> init() throws Exception {
        List<Tikun> tikunim = new ArrayList<>();
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        // Build a new authorized API client service.
        Sheets service = getSheetsService();

        // Prints the names and majors of students in a sample spreadsheet:
        // https://docs.google.com/spreadsheets/d/1PD-kU7ioIeWTuLXjqASxTBxd2xG9HdaGcWs5niu-mbY/edit
        String spreadsheetId = "1PD-kU7ioIeWTuLXjqASxTBxd2xG9HdaGcWs5niu-mbY";
        String range = "מ.ר מ-1.08.16!A:M";
        int count = 0;
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } else {

            for (List row : values) {
           //     if (((String) row.get(0)).equals("#")) {
           //         continue;
            //    }
                try {

                    Tikun e = new Tikun(new ObjectId().toString(),(String)row.get(0), LocalDate.parse((String) row.get(1), DateTimeFormatter.ofPattern("dd.MM.yyyy")), (String) row.get(2), (String) row.get(3), (String) row.get(4), (String) row.get(5), (String) row.get(6), (String) row.get(7),(String) row.get(8),(String) row.get(9),(String) row.get(10),(String) row.get(11),(String) row.get(12));
                    tikunim.add(e);
                    System.out.printf("%s, %s,%s,%s,%s\n", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                }
                catch (IndexOutOfBoundsException e){
                 try {
                     Tikun tik = new Tikun(new ObjectId().toString(), (String) row.get(0), LocalDate.parse((String) row.get(1), DateTimeFormatter.ofPattern("dd.MM.yyyy")), (String) row.get(2), (String) row.get(3), (String) row.get(4), (String) row.get(5), (String) row.get(6), (String) row.get(7), (String) row.get(8), (String) row.get(9), (String) row.get(10), (String) row.get(11), "");
                     tikunim.add(tik);
                     System.out.printf("%s, %s,%s,%s,%s\n", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                 }
                 catch (IndexOutOfBoundsException es){
                     count++;
                     continue;
                 }
                }
                catch (DateTimeException e){
                    System.out.println(e);
                    System.out.println(" "+e.getCause()+" "+e.getMessage());
                    count++;
                    continue;
                }
                catch (Exception e) {
                    System.out.println(e);
                    System.out.println(" "+e.getCause()+" "+e.getMessage());
                    count++;
                    continue;

                }
                count++;
            }
            System.out.println(count);

        }

        // UpdateQuery(service, spreadsheetId, count);
        return tikunim;
    }

    public static void UpdateQuery(Sheets service, String spreadsheetId, int count) throws IOException, InterruptedException {
        int loccount = 0;
        String range = "lst1!A" + (count + 1) + ":M";
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } else {

            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                try {
                    System.out.println("ROW0" + (String) row.get(0));
                    if (((String) row.get(0)).equals("")) {
                        continue;
                    }
                    System.out.println("Table Updates:");
                    System.out.printf("%s, %s,%s,%s,%s\n", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                    String mess = "תיקון " + (String) row.get(4) + " " + "מוכן ";
                    Message message = Message.creator(new PhoneNumber("+972587782290"),
                            new PhoneNumber("+17148808428"),
                            mess).create();

                    System.out.println("message " + message.getSid() + " sent");

                } catch (Exception e) {
                    loccount++;
                    continue;
                }
                loccount++;
            }
        }
        Thread.sleep(30000);
        UpdateQuery(service, spreadsheetId, count + loccount);
    }
}