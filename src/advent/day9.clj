(ns advent.day9
  (:require [advent.solution :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]
            [loom [graph :as lg]
                  [alg :as la]]))

(defn read-input
  [f]
  (let [lines (->> f
                   io/resource
                   io/reader
                   line-seq)
        data (map #(let [fields (str/split %1 #" ")]
                [(fields 0) (fields 2) (Integer/parseInt (fields 4))])
             lines)]
    data))

(defn shortest-path-length
  [wg]
  (apply min (let [weight (lg/weight wg)
                   edge->weight (map #(weight (vec %)))
                   sps (la/bf-all-pairs-shortest-paths wg)
                   edge-lists (for [o (keys sps)]
                                (partition 2 1 (map #(concat (keys %) (first (vals %))) (vals sps))))]
               (map #(transduce edge->weight + 0 %) edge-lists))))

(defn mkpaths
  [nodes]
  (for [i nodes
        j (set/difference nodes #{i})
        k (set/difference nodes #{i j})
        l (set/difference nodes #{i j k})
        m (set/difference nodes #{i j k l})
        n (set/difference nodes #{i j k l m})
        o (set/difference nodes #{i j k l m n})
        p (set/difference nodes #{i j k l m n o})]
    (partition 2 1 [i j k l m n o p])))

(defn brute-force
  [graph]
  (let [paths (mkpaths (lg/nodes graph))
        xf (map (fn [ps] (map #(lg/weight graph (vec %)) ps)))]
    (map #(reduce + 0 %) (transduce xf conj [] paths))))

(defsolution 9
  (let [graph (apply lg/weighted-graph (read-input "day9-input.txt"))
        ;shortest-path (shortest-path-length graph)
        brute-output (brute-force graph)
        min-brute-force (apply min brute-output)
        max-brute-force (apply max brute-output)]
    (println "Solution for day 9 part 1 is:" min-brute-force)
    (println "Solution for day 9 part 2 is:" max-brute-force)))
