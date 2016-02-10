(ns advent.day15
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [advent.solution :refer [defsolution]]))

(def ^:dynamic *properties* [:capacity :durability :flavor :texture])
(def ^:dynamic *property-indices* [2 4 6 8 10])

(defn parse-line
  [l]
  (into {} (map #(vector %1 (Integer/parseInt %2))
                (conj *properties* :calories)
                (-> l
                    (str/replace #"[,:]" "")
                    (str/split #" ")
                    (map *property-indices*)))))

(defn read-input
  [f]
  (->> f
       io/resource
       io/reader
       line-seq
       (map parse-line)))

(defn generate-recipes
  [limit]
  (let [limit' (inc limit)]
    (for [i (range limit')
          j (range (- limit' i))
          k (range (- limit' i j))]
      [i j k (- limit i j k)])))

(defn calc-prop
  [p is r]
  (apply + (map #(* (p %1) %2) is r)))

(defn make-cookie
  ([ingredients calorie-f recipe]
   (apply * (for [p *properties*
                  :when (calorie-f (calc-prop :calories ingredients recipe))
                  :let [t (calc-prop p ingredients recipe)]]
              (if (> t 0)
                t
                0))))
  ([ingredients recipe]
   (make-cookie ingredients (constantly true) recipe)))

(defn bake
  ([ingredients recipes calorie-f]
   (pmap (partial make-cookie ingredients calorie-f) recipes))
  ([ingredients recipes]
   (pmap (partial make-cookie ingredients) recipes)))

(defsolution 15
  (let [ingredients (read-input "day15-input.txt")
        recipes (generate-recipes 100)
        cookies-1 (bake ingredients recipes)
        cookies-2 (bake ingredients recipes #(= 500 %))]
    (show 1 (apply max cookies-1))
    (show 2 (apply max cookies-2))))
