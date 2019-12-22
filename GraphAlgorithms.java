/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

/**
 * A collection of graph algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs depth-first search of the unknown portion of Graph g starting at Vertex u.
     *
     * @param g      Graph instance
     * @param u      Vertex of graph g that will be the source of the search
     * @param known  is a set of previously discovered vertices
     * @param forest is a map from nonroot vertex to its discovery edge in DFS forest
     *               <p>
     *               As an outcome, this method adds newly discovered vertices (including u) to the known set,
     *               and adds discovery graph edges to the forest.
     */
    public static <V, E> void DFS(Graph<V, E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        System.out.println("visited vertex: " + u.getElement());
        known.add(u);                              // u has been discovered
        for (Edge<E> e : g.outgoingEdges(u)) {     // for every outgoing edge from u
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);                      // e is the tree edge that discovered v
                DFS(g, v, known, forest);              // recursively explore from v
            }
        }
    }


    /**
     * Performs DFS for the entire graph and returns the DFS forest as a map.
     *
     * @return map such that each nonroot vertex v is mapped to its discovery edge
     * (vertices that are roots of a DFS trees in the forest are not included in the map).
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> DFSComplete(Graph<V, E> g) {
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Edge<E>> forest = new ProbeHashMap<>();
        for (Vertex<V> u : g.vertices())
            if (!known.contains(u))
                DFS(g, u, known, forest);            // (re)start the DFS process at u
        return forest;
    }

    /**
     * Returns an ordered list of edges comprising the directed path from u to v.
     * If v is unreachable from u, or if u equals v, an empty path is returned.
     *
     * @param g Graph instance
     * @param u Vertex beginning the path
     * @param v Vertex ending the path
     */

    public static <V, E> PositionalList<Edge<E>> findPath(Graph<V, E> g, Vertex<V> u, Vertex<V> v) {
        PositionalList<Edge<E>> list = new LinkedPositionalList<>();
        if (u.equals(v)) return list;
        Set<Vertex<V>> set = new HashSet<>();
        Map<Vertex<V>, Edge<E>> forest = new ProbeHashMap<>();
        DFS(g, u, set, forest);
        for (Entry<Vertex<V>, Edge<E>> entry : forest.entrySet()) {
            System.out.println("entry  " + entry.getKey().getElement());
            if (!entry.getKey().equals(v)) {
                System.out.println("entry girdi " + entry.getValue().getElement());
                list.addLast(entry.getValue());
            } else if (entry.getKey().equals(v)) {
                list.addLast(entry.getValue());
                break;
            } else if (entry.getValue() == null) {
                while (list.size() > 0)
                    list.remove(list.first());
                return list;
            }

        }
        return list;
    }
    }
