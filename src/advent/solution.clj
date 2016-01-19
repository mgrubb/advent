(ns advent.solution)

(defmacro defsolution
  [day & blocks]
  `(defn ~'solution
     ([en#]
      (if en#
        (do
          ~@blocks)
        (println "Solution for day" ~day "is: skipped")))
     ([] (~'solution true))))
