(ns advent.solution)

(defmacro defsolution
  [day & blocks]
  `(defn ~'solution
     ([en#]
      (if en#
        (letfn [(~'show [p# sol#] (println "Solution for day" ~day "part" p# "is:"  sol#))]
          ~@blocks)
        (println "Solution for day" ~day "is: skipped")))
     ([] (~'solution true))))
