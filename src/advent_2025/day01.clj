(ns advent-2025.day01)

(defrecord Movement [direction distance])


(defn- prepare-movements
       [file-name]
       (map
             #(->Movement (subs % 0 1) (Integer. (subs % 1)))
             (clojure.string/split-lines (slurp file-name)))
       )

(defn- move
       [position {:keys [direction distance]}]
       (rem (cond
                  (= direction "R") (+ position distance)
                  :else (+ (- position distance) 100) ; force it to be positive
                  ) 100)
       )

(defn day01-part1
      [file-name]
      (->> (prepare-movements file-name)
           (reductions move 50)
           (filter zero?)
           (count)
           )
      )
