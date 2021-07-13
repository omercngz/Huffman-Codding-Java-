import java.util.*;
 
abstract class HuffmanAgacı implements Comparable<HuffmanAgacı> {
    public final int sıklık; //-------------------------------------------->1

    public HuffmanAgacı(int frekans) {
        sıklık = frekans; //----------------------------------------------->1
    }
 
    
    @Override
    public int compareTo(HuffmanAgacı tree) {
        return sıklık - tree.sıklık;//------------------------------------->1
    }
}//------------------------------------------------------------------------>TOPLAM = 3
 
class HuffmanYaprak extends HuffmanAgacı {
    public final char deger;//--------------------------------------------->1
 
    public HuffmanYaprak(int freq, char val) {
        super(freq);//----------------------------------------------------->karakterSayısı
        deger = val;//----------------------------------------------------->karakterSayısı
    }
}
 //------------------------------------------------------------------------>TOPLAM =2*karakterSayısı+1
class HuffmanDügüm extends HuffmanAgacı {
    public final HuffmanAgacı sol, sag; //--------------------------------->1
 
    public HuffmanDügüm(HuffmanAgacı l, HuffmanAgacı r) {
        super(l.sıklık + r.sıklık);//-------------------------------------->2*karakterSayısı
        sol = l;//--------------------------------------------------------->2*karakterSayısı
        sag = r;//--------------------------------------------------------->2*karakterSayısı
    }
}//------------------------------------------------------------------------>TOPLAM = 6*karakterSayısı+1
public class HuffmanKodu {
    
    public static HuffmanAgacı huffmanAgacı(int[] karakterFrekansları) {
   PriorityQueue<HuffmanAgacı> agac = new PriorityQueue<HuffmanAgacı>();//-->2*karakterSayısı
      
      
        for (int i = 0; i < karakterFrekansları.length; i++){//-------------->255
            if (karakterFrekansları[i] > 0){//------------------------------>karakterSayısı
        agac.offer(new HuffmanYaprak(karakterFrekansları[i], (char)i));//--->karakterSayısı
            }
        assert agac.size() > 0;//------------------------------------------->255
        }
        while (agac.size() > 1) {//------------------------------------->karakterSayısı
            
            HuffmanAgacı a = agac.poll();//----------------------------->karakterSayısı
            HuffmanAgacı b = agac.poll();//----------------------------->karakterSayısı
 
             
            agac.offer(new HuffmanDügüm(a, b));//------------------------>karakterSayısı
        }
        return agac.poll();//--------------------------------------------->1
    }//----------------------------------------------------------------->TOPLAM = 8*karakterSayısı+511
 
    public static void koduYazdırma(HuffmanAgacı agac, StringBuffer prefix) {
        assert agac != null;//--------------------------------------------->1
        if (agac instanceof HuffmanYaprak) {//----------------------------->1
            HuffmanYaprak yaprak = (HuffmanYaprak)agac;//------------------>1
 
            System.out.println(yaprak.deger + "\t" + yaprak.sıklık + "\t" + prefix);//------->1
 
        } else if (agac instanceof HuffmanDügüm) {//----------------------->1
            HuffmanDügüm node = (HuffmanDügüm)agac;//---------------------->1
 
           
            prefix.append('0');//------------------------------------------>1
            koduYazdırma(node.sol, prefix);//------------------------------>1
            prefix.deleteCharAt(prefix.length()-1);//---------------------->1
 
            
            prefix.append('1');//------------------------------------------>1
            koduYazdırma(node.sag, prefix);//------------------------------>1
            prefix.deleteCharAt(prefix.length()-1);//---------------------->1
        }
    }//-------------------------------------------------------------------->TOPLAM = 12
 
    public static void main(String[] args) {
        String test = "ALGORITMAANALIZI";//---------------------->1
 
        int[] charFrekans = new int[255];//---------------------->1
        
        for (char c : test.toCharArray()){//---------------------->255
              
            charFrekans[c]++;//----------------------------------->255
        }
        HuffmanAgacı agac = huffmanAgacı(charFrekans);//----------->1
 
        System.out.println("Karakter|\tTekrar|\tHUFFMAN Kodu");//-->1
        koduYazdırma(agac, new StringBuffer());//------------------>1
        System.out.println("Çalış zamanı = "+System.nanoTime());//--->516
        //----------------------------------------------------------->TOPLAM = 16*karakterSayısı+1044
    }
}