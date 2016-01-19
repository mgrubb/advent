(ns advent.day6
  (:require [advent.solution :refer :all]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn- meta-key [obj k] (get (meta obj) k))
(defn- persistent?! [coll]
  (if (isa? (class coll) clojure.lang.IPersistentCollection)
    coll
    (persistent! coll)))

(defn switch-mode
  [op _ v]
  (case op
        :on 1
        :off 0
        :toggle (if (zero? v) 1 0)))

(defn brightness-mode
  [op _ v]
  (case op
    :on (inc v)
    :off (max (dec v) 0)
    :toggle (+ 2 v))) 

(defn make-grid
  ([n mode default] 
   (with-meta (vec (repeat (* n n) default)) {:dim n
                                              :mode mode}))
  ([n mode] (make-grid n mode 0))
  ([n] (make-grid n switch-mode 0)))

(defn coord->idx
  [g [x y :as coord]]
  (+ (* y (meta-key g :dim)) x))

(defn range->idxs
  [g [x1 y1 :as p1] [x2 y2 :as p2]]
  (sort (for [x (range x1 (inc x2))
              y (range y1 (inc y2))]
          (coord->idx g [x y]))))

(defn parse-command
  [cmd]
  (letfn
    [(parse-coord [s] (vec (map #(Integer/parseInt %) (str/split s #","))))
     (parse-op [parts]
       (if (= (first parts) "toggle")
         [:toggle (drop 1 parts)]
         [(keyword (second parts)) (drop 2 parts)]))]

    (let [parts (str/split cmd #" ")
          [op parts] (parse-op parts)
          [p1 _ p2] parts
          p1 (parse-coord p1)
          p2 (parse-coord p2)]
      {:op op :p1 p1 :p2 p2})))

(defn do-command
  [grid cmd]
  (let [{:keys [p1 p2 op]} cmd
        idxs (range->idxs grid p1 p2)
        mode (meta-key grid :mode)]
    (loop [g grid [i & idxs] idxs]
      (if i
        (recur (assoc g i (mode op i (g i))) idxs)
        g))))

(defn do-commands
  [grid [cmd & cmds]]
  (if cmd
    (recur (do-command grid cmd) cmds)
    grid))

(defn print-grid
  [g]
  (let [dim (meta-key g :dim)]
    (doseq [r (partition dim g)]
      (println r))))

(defn set-lights
  ([grid data]
   (let [cmds (map parse-command data)]
     (loop [g grid [cmd & cmds] cmds]
       (if cmd
         (recur (do-command g cmd) cmds)
         g))))
  ([g] (set-lights g (line-seq (io/reader (io/resource "day6-input.txt"))))))

(defn light-count
  [g]
  (count (filter #{1} (set-lights g))))

(defn brightness
  [g]
  (reduce + (set-lights g)))

(defsolution 6
  (let [g1 (make-grid 1000)
        g2 (make-grid 1000 brightness-mode)]
    (println "Solution for day 6 part 1 is:" (light-count g1))
    (println "Solution for day 6 part 2 is:" (brightness g2))))
