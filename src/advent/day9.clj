(ns advent.day9
  (:require [advent.solution :refer :all]
            [advent.util :refer [permutations]]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn mkdisthash
  [input]
  (loop [[i & inputs] input dmap (transient {})]
    (if i
      (let [k1 (vec (take 2 i))
            k2 (vec (reverse k1))
            v (nth i 2)]
        (recur inputs (assoc! dmap k1 v k2 v)))
      (persistent! dmap))))

(defn read-input
  [f]
  (let [lines (->> f
                   io/resource
                   io/reader
                   line-seq)]
    (map #(let [fields (str/split %1 #" ")]
            [(fields 0) (fields 2) (Integer/parseInt (fields 4))]) lines)))

(defn nodes
  [data]
  (set (apply concat (keys data))))

(defn- filter-nodes
  [path nodes]
  (remove (set path) nodes))

(defn all-paths
  [nodes]
  (permutations nodes))

(defn split-path
  [path]
  (mapv #(vec %) (partition 2 1 path)))

(defn path-length
  [dists path] 
  (transduce (map #(dists %)) + 0 (split-path path)))

(defsolution 9
  (let [dmap (-> "day9-input.txt" read-input mkdisthash)
        nodes (nodes dmap)
        paths (all-paths nodes)
        lengths (map (partial path-length dmap) paths)
        min-length-path (apply min lengths)
        max-length-path (apply max lengths)]
    (println "Solution for day 9 part 1 is:" min-length-path)
    (println "Solution for day 9 part 2 is:" max-length-path)))
