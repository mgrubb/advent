(ns advent.day12
  (:require [clojure.java.io :as io]
            [clojure.walk :refer [postwalk prewalk]]
            [clojure.data.json :as json]
            [advent.solution :refer [defsolution]]))

(defn read-input
  [f]
  (-> f
      io/resource
      io/reader
      (json/read :key-fn keyword)))

(defn sum-walk
  [d]
  (let [res (volatile! 0)]
    (postwalk #(if (number? %)
                 (vswap! res + %)
                 %) d)
    @res))

(defn filter-red
  [d]
  (prewalk #(if (and (map? %) (some #{"red"} (vals %))) nil %) d))

(defsolution 12
  (let [d (read-input "day12-input.txt")
        d' (filter-red d)]
    (show 1 (sum-walk d))
    (show 2 (sum-walk d'))))

