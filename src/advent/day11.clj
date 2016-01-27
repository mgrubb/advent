(ns advent.day11
  (:require [advent.solution :refer [defsolution]]))

(def ^:dynamic *alpha-start* (int \a))
(def ^:dynamic *base* 26)

(defn char->num
  [c]
  (- (int c) *alpha-start*))

(defn num->char
  [n]
  (char (+ n *alpha-start*)))

;; Thank you slj/advent https://github.com/sjl/advent/blob/master/advent.lisp#L598
(defn inc-nums
  [nums]
  (letfn [(mod+ [x] (mod (inc x) *base*))]
    (reverse (loop [[x & xs] (reverse nums) res []]
               (if x
                 (let [h (mod+ x)]
                   (if (zero? h)
                     (recur xs (conj res h))
                     (concat (conj res h) xs)))
                 (conj res 1))))))

(defn str->nums
  [s]
  (mapv char->num s))

(defn inc-str
  [s]
  (->> s
       str
       (map char->num)
       inc-nums
       (map num->char)
       (apply str)))

(defn no-bad-chars?
  [s]
  (not (some #{\i \o \l} s)))

(defn has-straight?
  [s]
  (letfn [(is-run? [[x y z]] (= x (dec y) (- z 2)))]
    (->> s
      str->nums
      (partition 3 1)
      (some is-run?)
      some?)))

(defn has-pairs?
  [s]
  (let [xf (comp
             (partition-by identity)
             (map count)
             (filter #{2}))
        cnt (->> s str->nums (transduce xf + 0))]
    (if (>= cnt 4)
      true
      false)))

(defn valid-password?
  [s]
  (and (no-bad-chars? s)
       (has-pairs? s)
       (has-straight? s)))

(defn find-next-password
  ([s]
   (loop [s (inc-str s)]
     (if (valid-password? s)
       s
       (recur (inc-str s)))))
  ([] (find-next-password "aabcc")))

(defsolution 11
  (let [nextp (find-next-password "hepxcrrq")]
    (println "Solution for day 11 part 1 is:" nextp)
    (println "Solution for day 11 part 2 is:" (find-next-password nextp))))
