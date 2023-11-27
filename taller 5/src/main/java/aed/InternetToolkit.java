package aed;

public class InternetToolkit {
    public InternetToolkit() {
    }

    private Fragment[] insertionSort(Fragment[] input) {
        int largo = input.length;
        if (largo > 0) {
            int i = 1;
            while (i < largo) {
                int j = i;
                while (j > 0 && input[j - 1].compareTo(input[j]) > 0) {
                    Fragment aux = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = aux;
                    j--;
                }
                i++;
            }
        }
        return input;
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        return insertionSort(fragments);
    }

    

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        MaxHeap RouterRanking = new MaxHeap(routers.length);
        Router[] kTopRouters = new Router[k];
        //cargo el array
        for (int i = 0; i < routers.length; i++) {
            Router router = routers[i]; 
            if (router.getTrafico() > umbral) {
                RouterRanking.insert(router, router.getTrafico());
            }
        }
        int i = 0;
        while (i < k) {
            kTopRouters[i] = (Router) RouterRanking.extractMax();
            i++;
        }
        
        return kTopRouters;
    }


    public IPv4Address[] sortIPv4(String[] ipv4) {
        IPv4Address[] sortIPv4 =  new IPv4Address[ipv4.length];
        for (int i = 0; i < ipv4.length; i++) {
            sortIPv4[i] = new IPv4Address(ipv4[i]);
        }
        int i = 3;
        while (i>=0) {
            ListaEnlazada<IPv4Address>[] bucket = new ListaEnlazada[256];
            for (int j = 0; j < ipv4.length; j++) {
                IPv4Address ip = sortIPv4[j];
                if(bucket[ip.getOctet(i)] != null){
                    bucket[ip.getOctet(i)].agregarAtras(ip);
                }else{
                    bucket[ip.getOctet(i)] = new ListaEnlazada();
                    bucket[ip.getOctet(i)].agregarAtras(ip);
                }
            }
            int k = 0;
            for (int j = 0; j < 256; j++) { 
                if(bucket[j] != null){
                    while (bucket[j].longitud() > 0) {
                        sortIPv4[k] = bucket[j].obtener(0);
                        bucket[j].eliminar(0);
                        k++;
                    }
                    
                }
            }
            i--;
        }
        
        return sortIPv4;
    }

}
