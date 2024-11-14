import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Food implements Serializable
{
    int itemno;
    int quantity;
    float price;

    Food(int itemno,int quantity)
    {
        this.itemno=itemno;
        this.quantity=quantity;
        switch(itemno)
        {
            case 1:price=quantity*50;
                break;
            case 2:price=quantity*60;
                break;
            case 3:price=quantity*70;
                break;
            case 4:price=quantity*30;
                break;
        }
    }
}
class Singleroom implements Serializable
{
    String name;
    String contact;
    String gender;
    ArrayList<Food> food =new ArrayList<>();


    Singleroom()
    {
        this.name="";
    }
    Singleroom(String name,String contact,String gender)
    {
        this.name=name;
        this.contact=contact;
        this.gender=gender;
    }
}
class Doubleroom extends Singleroom implements Serializable
{
    String name2;
    String contact2;
    String gender2;

    Doubleroom()
    {
        this.name="";
        this.name2="";
    }
    Doubleroom(String name,String contact,String gender,String name2,String contact2,String gender2)
    {
        this.name=name;
        this.contact=contact;
        this.gender=gender;
        this.name2=name2;
        this.contact2=contact2;
        this.gender2=gender2;
    }
}
class NotAvailable extends Exception
{
    @Override
    public String toString()
    {
        return "Mevcut değil !";
    }
}

class holder implements Serializable
{
    Doubleroom luxury_doublerrom[]=new Doubleroom[10]; //Luxury
    Doubleroom deluxe_doublerrom[]=new Doubleroom[20]; //Deluxe
    Singleroom luxury_singleerrom[]=new Singleroom[10]; //Luxury
    Singleroom deluxe_singleerrom[]=new Singleroom[20]; //Deluxe
}

class Hotel
{
    static holder hotel_ob=new holder();
    static Scanner sc = new Scanner(System.in);
    static void CustDetails(int i,int rn)
    {
        String name, contact, gender;
        String name2 = null, contact2 = null;
        String gender2="";
        System.out.print("\nMüşteri adını girin: ");
        name = sc.next();
        System.out.print("İletişim numarasını girin: ");
        contact=sc.next();
        System.out.print("Cinsiyet girin: ");
        gender = sc.next();
        if(i<3)
        {
            System.out.print("İkinci müşteri adını girin: ");
            name2 = sc.next();
            System.out.print("İletişim numarasını girin: ");
            contact2=sc.next();
            System.out.print("Cinsiyet girin: ");
            gender2 = sc.next();
        }

        switch (i) {
            case 1:hotel_ob.luxury_doublerrom[rn]=new Doubleroom(name,contact,gender,name2,contact2,gender2);
                break;
            case 2:hotel_ob.deluxe_doublerrom[rn]=new Doubleroom(name,contact,gender,name2,contact2,gender2);
                break;
            case 3:hotel_ob.luxury_singleerrom[rn]=new Singleroom(name,contact,gender);
                break;
            case 4:hotel_ob.deluxe_singleerrom[rn]=new Singleroom(name,contact,gender);
                break;
            default:System.out.println("Yanlış seçenek");
                break;
        }
    }
    private static Staff hotelManager = new Staff("Ahmet", "Otel Müdürü");


    static void introduceHotelManager() {
        hotelManager.introduce();
    }
    private static ActivityTracker activityTracker = new ActivityTracker();

    static void trackActivity(String activity) {
        activityTracker.addActivity(activity);
    }

    static void showActivities() {
        activityTracker.displayActivities();
    }
    private static Guest currentGuest;

    static void addGuest(String name, String contact, String gender) {
        currentGuest = new Guest(name, contact, gender);
        System.out.println("Misafir bilgileri başarıyla kaydedildi.");
    }

    static void displayGuestDetails() {
        if (currentGuest != null) {
            currentGuest.displayDetails();
        } else {
            System.out.println("Misafir bilgileri kaydedilmemiş.");
        }
    }
    private static SpecialOffer currentOffer;

    static void addSpecialOffer(String offerName, double discountRate, String details) {
        currentOffer = new SpecialOffer(offerName, discountRate, details);
        System.out.println("Özel teklif detayları başarıyla kaydedildi.");
    }

    static void displaySpecialOfferDetails() {
        if (currentOffer != null) {
            currentOffer.displayDetails();
        } else {
            System.out.println("Özel teklif detayları kaydedilmemiş.");
        }
    }

    static void bookroom(int i)
    {
        int j;
        int rn;
        System.out.println("\nOda numarasını şuradan seçin : ");
        switch (i) {
            case 1:
                for(j=0;j<hotel_ob.luxury_doublerrom.length;j++)
                {
                    if(hotel_ob.luxury_doublerrom[j]==null)
                    {
                        System.out.print(j+1+",");
                    }
                }
                System.out.print("\nOda numarasını girin: ");
                try{
                    rn=sc.nextInt();
                    rn--;
                    if(hotel_ob.luxury_doublerrom[rn]!=null)
                        throw new NotAvailable();
                    CustDetails(i,rn);
                }
                catch(Exception e)
                {
                    System.out.println("Geçersiz Seçenek");
                    return;
                }
                break;
            case 2:
                for(j=0;j<hotel_ob.deluxe_doublerrom.length;j++)
                {
                    if(hotel_ob.deluxe_doublerrom[j]==null)
                    {
                        System.out.print(j+11+",");
                    }
                }
                System.out.print("\nOda numarasını girin: ");
                try{
                    rn=sc.nextInt();
                    rn=rn-11;
                    if(hotel_ob.deluxe_doublerrom[rn]!=null)
                        throw new NotAvailable();
                    CustDetails(i,rn);
                }
                catch(Exception e)
                {
                    System.out.println("Geçersiz Seçenek");
                    return;
                }
                break;
            case 3:
                for(j=0;j<hotel_ob.luxury_singleerrom.length;j++)
                {
                    if(hotel_ob.luxury_singleerrom[j]==null)
                    {
                        System.out.print(j+31+",");
                    }
                }
                System.out.print("\nOda numarasını girin: ");
                try{
                    rn=sc.nextInt();
                    rn=rn-31;
                    if(hotel_ob.luxury_singleerrom[rn]!=null)
                        throw new NotAvailable();
                    CustDetails(i,rn);
                }
                catch(Exception e)
                {
                    System.out.println("Geçersiz Seçenek");
                    return;
                }
                break;
            case 4:
                for(j=0;j<hotel_ob.deluxe_singleerrom.length;j++)
                {
                    if(hotel_ob.deluxe_singleerrom[j]==null)
                    {
                        System.out.print(j+41+",");
                    }
                }
                System.out.print("\nOda numarasını girin: ");
                try{
                    rn=sc.nextInt();
                    rn=rn-41;
                    if(hotel_ob.deluxe_singleerrom[rn]!=null)
                        throw new NotAvailable();
                    CustDetails(i,rn);
                }
                catch(Exception e)
                {
                    System.out.println("Geçersiz Seçenek");
                    return;
                }
                break;
            default:
                System.out.println("Geçerli seçeneği girin");
                return;
        }
        System.out.println("Oda Rezervasyonu Yapıldı");
    }

    static void features(int i)
    {
        switch (i) {
            case 1:System.out.println("Çift kişilik yatak sayısı : 1\nAC : Evet\nÜcretsiz kahvaltı : Evet\nGünlük ücretlendirme : 4000 ");
                break;
            case 2:System.out.println("Çift kişilik yatak sayısı : 1\nAC : Hayır\nÜcretsiz kahvaltı : Evet\nGünlük ücretlendirme : 3000  ");
                break;
            case 3:System.out.println("Tek kişilik yatak sayısı : 1\nAC : Evet\nÜcretsiz kahvaltı : Evet\nGünlük ücretlendirme : 2200  ");
                break;
            case 4:System.out.println("Tek kişilik yatak sayısı : 1\nAC : Hayır\nÜcretsiz kahvaltı : Evet\nGünlük ücretlendirme : 1200 ");
                break;
            default:
                System.out.println("Geçerli seçeneği girin");
                break;
        }
    }

    static void availability(int i)
    {
        int j,count=0;
        switch (i) {
            case 1:
                for(j=0;j<10;j++)
                {
                    if(hotel_ob.luxury_doublerrom[j]==null)
                        count++;
                }
                break;
            case 2:
                for(j=0;j<hotel_ob.deluxe_doublerrom.length;j++)
                {
                    if(hotel_ob.deluxe_doublerrom[j]==null)
                        count++;
                }
                break;
            case 3:
                for(j=0;j<hotel_ob.luxury_singleerrom.length;j++)
                {
                    if(hotel_ob.luxury_singleerrom[j]==null)
                        count++;
                }
                break;
            case 4:
                for(j=0;j<hotel_ob.deluxe_singleerrom.length;j++)
                {
                    if(hotel_ob.deluxe_singleerrom[j]==null)
                        count++;
                }
                break;
            default:
                System.out.println("Geçerli seçeneği girin");
                break;
        }
        System.out.println("Mevcut oda sayısı : "+count);
    }

    static void bill(int rn,int rtype)
    {
        double amount=0;
        String list[]={"Sandviç","Makarna","Noodle","Kola"};
        System.out.println("\n*******");
        System.out.println(" Fatura:-");
        System.out.println("*******");

        switch(rtype)
        {
            case 1:
                amount+=4000;
                System.out.println("\nOda Ücreti - "+4000);
                System.out.println("\n===============");
                System.out.println("Yemek Ücretleri:- ");
                System.out.println("===============");
                System.out.println("Ürün    Miktar    Fiyat");
                System.out.println("-------------------------");
                for(Food obb:hotel_ob.luxury_doublerrom[rn].food)
                {
                    amount+=obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                }

                break;
            case 2:amount+=3000;
                System.out.println("Oda Ücreti - "+3000);
                System.out.println("\nYemek Ücretleri:- ");
                System.out.println("===============");
                System.out.println("Ürün    Miktar    Fiyat");
                System.out.println("-------------------------");
                for(Food obb:hotel_ob.deluxe_doublerrom[rn].food)
                {
                    amount+=obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                }
                break;
            case 3:amount+=2200;
                System.out.println("Oda Ücreti - "+2200);
                System.out.println("\nYemek Ücretleri:- ");
                System.out.println("===============");
                System.out.println("Ürün    Miktar    Fiyat");
                System.out.println("-------------------------");
                for(Food obb:hotel_ob.luxury_singleerrom[rn].food)
                {
                    amount+=obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                }
                break;
            case 4:amount+=1200;
                System.out.println("Oda Ücreti - "+1200);
                System.out.println("\nYemek Ücretleri:- ");
                System.out.println("===============");
                System.out.println("Ürün    Miktar    Fiyat");
                System.out.println("-------------------------");
                for(Food obb: hotel_ob.deluxe_singleerrom[rn].food)
                {
                    amount+=obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format,list[obb.itemno-1],obb.quantity,obb.price );
                }
                break;
            default:
                System.out.println("Geçerli değil");
        }
        System.out.println("\nToplam Tutar- "+amount);
    }

    static void deallocate(int rn,int rtype)
    {
        int j;
        char w;
        switch (rtype) {
            case 1:
                if(hotel_ob.luxury_doublerrom[rn]!=null)
                    System.out.println("Tarafından kullanılan oda "+hotel_ob.luxury_doublerrom[rn].name);
                else
                {
                    System.out.println("Zaten Boş");
                    return;
                }
                System.out.println("Ödeme yapmak istiyor musunuz ?(y/n)");
                w=sc.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.luxury_doublerrom[rn]=null;
                    System.out.println("Tahsis başarıyla kaldırıldı");
                }

                break;
            case 2:
                if(hotel_ob.deluxe_doublerrom[rn]!=null)
                    System.out.println("Tarafından kullanılan oda "+hotel_ob.deluxe_doublerrom[rn].name);
                else
                {
                    System.out.println("Zaten Boş");
                    return;
                }
                System.out.println("Ödeme yapmak istiyor musunuz ?(y/n)");
                w=sc.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.deluxe_doublerrom[rn]=null;
                    System.out.println("Tahsis başarıyla kaldırıldı");
                }

                break;
            case 3:
                if(hotel_ob.luxury_singleerrom[rn]!=null)
                    System.out.println("Tarafından kullanılan oda "+hotel_ob.luxury_singleerrom[rn].name);
                else
                {
                    System.out.println("Zaten Boş");
                    return;
                }
                System.out.println("Ödeme yapmak istiyor musunuz ? (y/n)");
                w=sc.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.luxury_singleerrom[rn]=null;
                    System.out.println("Tahsis başarıyla kaldırıldı");
                }

                break;
            case 4:
                if(hotel_ob.deluxe_singleerrom[rn]!=null)
                    System.out.println("Tarafından kullanılan oda "+hotel_ob.deluxe_singleerrom[rn].name);
                else
                {
                    System.out.println("Zaten Boş");
                    return;
                }
                System.out.println("Ödeme yapmak istiyor musunuz ? (y/n)");
                w=sc.next().charAt(0);
                if(w=='y'||w=='Y')
                {
                    bill(rn,rtype);
                    hotel_ob.deluxe_singleerrom[rn]=null;
                    System.out.println("Tahsis başarıyla kaldırıldı");
                }
                break;
            default:
                System.out.println("\nGeçerli seçeneği girin : ");
                break;
        }
    }

    static void order(int rn,int rtype)
    {
        int i,q;
        char wish;
        try{
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandviç\tRs.50\n2.Makarna\tRs.60\n3.Noodle\tRs.70\n4.Kola\t\tRs.30\n");
            do
            {
                i = sc.nextInt();
                System.out.print("Miktar- ");
                q=sc.nextInt();

                switch(rtype){
                    case 1: hotel_ob.luxury_doublerrom[rn].food.add(new Food(i,q));
                        break;
                    case 2: hotel_ob.deluxe_doublerrom[rn].food.add(new Food(i,q));
                        break;
                    case 3: hotel_ob.luxury_singleerrom[rn].food.add(new Food(i,q));
                        break;
                    case 4: hotel_ob.deluxe_singleerrom[rn].food.add(new Food(i,q));
                        break;
                }
                System.out.println("Başka bir şey sipariş etmek ister misiniz? ? (y/n)");
                wish=sc.next().charAt(0);
            }while(wish=='y'||wish=='Y');
        }
        catch(NullPointerException e)
        {
            System.out.println("\nOda rezerve edilmedi");
        }
        catch(Exception e)
        {
            System.out.println("Yapılamıyor");
        }
    }
}
class Staff {
    private String name;
    private String position;

    Staff(String name, String position) {
        this.name = name;
        this.position = position;
    }

    void introduce() {
        System.out.println("Ben " + name + ", " + position + " pozisyonundayım.");
    }
}
class ActivityTracker implements Serializable {
    private List<String> activities;

    ActivityTracker() {
        activities = new ArrayList<>();
    }

    void addActivity(String activity) {
        activities.add(activity);
        System.out.println("Aktivite kaydedildi: " + activity);
    }

    void displayActivities() {
        System.out.println("Otel Aktiviteleri:");
        for (String activity : activities) {
            System.out.println("- " + activity);
        }
    }
}
class Guest implements Serializable {
    private String name;
    private String contact;
    private String gender;

    Guest(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    void displayDetails() {
        System.out.println("Misafir Bilgileri:");
        System.out.println("Adı: " + name);
        System.out.println("İletişim Bilgileri: " + contact);
        System.out.println("Cinsiyet: " + gender);
    }
}
class SpecialOffer implements Serializable {
    private String offerName;
    private double discountRate;
    private String details;

    SpecialOffer(String offerName, double discountRate, String details) {
        this.offerName = offerName;
        this.discountRate = discountRate;
        this.details = details;
    }

    void displayDetails() {
        System.out.println("Özel Teklif Detayları:");
        System.out.println("Teklif Adı: " + offerName);
        System.out.println("İndirim Oranı: %" + discountRate);
        System.out.println("Detaylar: " + details);
    }
}
class CleaningStatus implements Serializable {
    private boolean isClean;

    CleaningStatus(boolean isClean) {
        this.isClean = isClean;
    }

    void displayStatus() {
        if (isClean) {
            System.out.println("Oda temiz.");
        } else {
            System.out.println("Oda temiz değil.");
        }
    }
}
class SpecialRequest implements Serializable {
    private String requestDetails;

    SpecialRequest(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    void displayDetails() {
        System.out.println("Özel Talep Detayları: " + requestDetails);
    }
}
class ExtraService implements Serializable {
    private String serviceName;
    private double serviceCost;

    ExtraService(String serviceName, double serviceCost) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }

    void displayDetails() {
        System.out.println("Ekstra Hizmet Detayları: " + serviceName);
        System.out.println("Hizmet Ücreti: " + serviceCost);
    }
}


class write implements Runnable
{
    holder hotel_ob;
    write(holder hotel_ob)
    {
        this.hotel_ob=hotel_ob;
    }
    @Override
    public void run() {
        try{
            FileOutputStream fout=new FileOutputStream("yedekleme");
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            oos.writeObject(hotel_ob);
        }
        catch(Exception e)
        {
            System.out.println("Yazım hatası "+e);
        }

    }

}

public class Main {
    public static void main(String[] args){

        try
        {
            File f = new File("yedekleme");
            if(f.exists())
            {
                FileInputStream fin=new FileInputStream(f);
                ObjectInputStream ois=new ObjectInputStream(fin);
                Hotel.hotel_ob=(holder)ois.readObject();
            }
            Scanner sc = new Scanner(System.in);
            int ch,ch2;
            char wish;
            x:
            do{

                System.out.println("\nSeçiminizi girin :\n1.Oda ayrıntılarını görüntüle\n2.Oda müsaitliğini göster \n3.Rezervasyon\n4.Yemek siparişi\n5.Ödeme\n6.Çıkış\n");
                ch = sc.nextInt();
                switch(ch){
                    case 1: System.out.println("\nOda tipini seçin :\n1.Lüks Çift Kişilik Oda \n2.Delüks Çift Kişilik Oda \n3.Lüks Tek Kişilik Oda\n4.Delüks Tek Kişilik Oda\n");
                        ch2 = sc.nextInt();
                        Hotel.features(ch2);
                        break;
                    case 2:System.out.println("\nOda tipini seçin :\n1.Lüks Çift Kişilik Oda \n2.Delüks Çift Kişilik Oda \n3.Lüks Tek Kişilik Oda\n4.Delüks Tek Kişilik Oda\n");
                        ch2 = sc.nextInt();
                        Hotel.availability(ch2);
                        break;
                    case 3:System.out.println("\nOda tipini seçin :\n1.Lüks Çift Kişilik Oda \n2.Delüks Çift Kişilik Oda \n3.Lüks Tek Kişilik Oda\n4.Delüks Tek Kişilik Oda\n");
                        ch2 = sc.nextInt();
                        Hotel.bookroom(ch2);
                        break;
                    case 4:
                        System.out.print("Oda Numarası -");
                        ch2 = sc.nextInt();
                        if(ch2>60)
                            System.out.println("Oda mevcut değil");
                        else if(ch2>40)
                            Hotel.order(ch2-41,4);
                        else if(ch2>30)
                            Hotel.order(ch2-31,3);
                        else if(ch2>10)
                            Hotel.order(ch2-11,2);
                        else if(ch2>0)
                            Hotel.order(ch2-1,1);
                        else
                            System.out.println("Oda mevcut değil");
                        break;
                    case 5:
                        System.out.print("Oda Numarası -");
                        ch2 = sc.nextInt();
                        if(ch2>60)
                            System.out.println("Oda mevcut değil");
                        else if(ch2>40)
                            Hotel.deallocate(ch2-41,4);
                        else if(ch2>30)
                            Hotel.deallocate(ch2-31,3);
                        else if(ch2>10)
                            Hotel.deallocate(ch2-11,2);
                        else if(ch2>0)
                            Hotel.deallocate(ch2-1,1);
                        else
                            System.out.println("Oda mevcut değil");
                        break;
                    case 6:break x;

                }

                System.out.println("\nDevam et : (y/n)");
                wish=sc.next().charAt(0);
                if(!(wish=='y'||wish=='Y'||wish=='n'||wish=='N'))
                {
                    System.out.println("Geçersiz Seçenek");
                    System.out.println("\nDevam et : (y/n)");
                    wish=sc.next().charAt(0);
                }

            }while(wish=='y'||wish=='Y');

            Thread t=new Thread(new write(Hotel.hotel_ob));
            t.start();
        }
        catch(Exception e)
        {
            System.out.println("Geçerli bir girdi değil");
        }
    }
}
