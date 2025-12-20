(ns advent-2025.day01)

(defrecord Movement [direction distance])


(defn- prepare-movements
       [file-name]
       (map
             #(->Movement (subs % 0 1) (Integer. (subs % 1)))
             (clojure.string/split-lines (slurp file-name)))
       )

(defn- move
       [position movement]
       (rem (cond
                  (= (:direction movement) "R") (+ position (:distance movement))
                  :else (+ (- position (:distance movement)) 100) ; force it to be positive
                  ) 100)
       )

(defn day01-a-main
      [file-name]
      (def movements (prepare-movements file-name))
      (let [positions (reductions move 50 movements)
            zero-count (count (filter zero? positions))]
           zero-count
           )
      )

