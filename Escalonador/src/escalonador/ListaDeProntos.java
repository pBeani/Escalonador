package escalonador;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ListaDeProntos {

    final List<Processo> listaProntos = new ArrayList<Processo>();
    private boolean inicioPrograma = true;
    TabelaDeProcessos tabelaProcessos;

    public void ordenaListaProntos() {
        Collections.sort(listaProntos, new Comparator<Processo>() {

            public int compare(Processo o1, Processo o2) {
                Processo p1 = (Processo) o1;
                Processo p2 = (Processo) o2;

                //se o credito de p1 for maior, ele deve ser o primeiro da lista
                if (p1.getCredito() > p2.getCredito()) {
                    return -1;
                }

                //se o credito de p1 for menor, p2 deve continuar na frente
                if (p1.getCredito() < p2.getCredito()) {
                    return 1;
                }

                //se os creditos forem iguais, tratar os casos
                if (p1.getCredito() == p2.getCredito()) {
                    //prioridades iguais quando inicia-se o programa
                    if (inicioPrograma == true) {
                        if (p1.bcp.getNome().compareToIgnoreCase(p2.bcp.getNome()) < 0) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }

                    //credito empata, mas a prioridade do que estava antes era maior
                    if (p1.bcp.getPrioridade() > p2.bcp.getPrioridade()) {
                        return -1;
                    }
                    if (p2.bcp.getPrioridade() < p2.bcp.getPrioridade()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                return 0;

            }
        });
    }

    public void inserirListaProntos(Processo processo) {
        listaProntos.add(processo);
        ordenaListaProntos();
    }

    public void removerListaProntos(Processo processo) {
        listaProntos.remove(processo);
    }
    public void refazerListaDeProntos() {
    	for(Processo processo :tabelaProcessos.getTabelaProcesso()) {
    		inserirListaProntos(processo);
    	}
    	ordenaListaProntos();
    }

	
		
	
}
