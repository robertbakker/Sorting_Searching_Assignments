package nl.hva.robertmark.sortingsearching.practicum2;

import nl.hva.dmci.ict.sortingsearching.weigthedgraph.AdjMatrixEdgeWeightedDigraph;
import nl.hva.dmci.ict.sortingsearching.weigthedgraph.EdgeWeightedDigraph;

public class MainPracticum2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("dijkstra");
        getAllDijkstra();
        System.out.println("---------------------------");
        System.out.println("floyd-warshall");
        getAllFloyd();
    }

    static void getAllDijkstra() {
        for (int i = 1; i <= 24; i++) {
            try {
                String naam = "i" + Integer.toString(i);
                String mapnaam = "Map " + Integer.toString(i);
                EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(naam);
                Dijkstra dijkstra = new Dijkstra(ewd, ewd.getStart());
                ewd.tekenPad(dijkstra.pathTo(ewd.getEnd(), naam));
                ewd.show(naam, " dijkstra");
                ewd.save(naam + " dijkstra");
            } catch (Exception e) {

            }
        }
    }

    static void getAllFloyd() {
        for (int i = 1; i <= 24; i++) {
//            if(i != 2 && i !=6 && i!=10 && i!=12 && i!=21){
            String naam = "i" + Integer.toString(i);
            String mapnaam = "Map " + Integer.toString(i);
            EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(naam);
            AdjMatrixEdgeWeightedDigraph amewd = ewd.createAdjMatrixEdgeWeightedDigraph();
            FloydWarshall floyd = new FloydWarshall(amewd);
            ewd.tekenPad(floyd.path(ewd.getStart(), ewd.getEnd(), naam));
            ewd.show(naam, " floyd");
            ewd.save(naam + " floyd");
//            }
        }
    }
}
