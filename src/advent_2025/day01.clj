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
                  :else (abs (+ (- position (rem distance 100))  100)) ; force it to be positive
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


(defn crossing-zero-through-subtraction
      [pos distance]
      (if (and (not= pos 0) (<= (- pos distance) 0)) 1 0)
      )

(defn crossing-zero-through-full-rounds
      [pos distance operation]
      (quot (operation pos distance) 100)
      )


(defn crossing-zero-full-count
      [pos  {:keys [direction distance]}]
      (let [crossing-zero-partial (partial crossing-zero-through-full-rounds pos distance)]
           (cond
             (= direction "R") (crossing-zero-partial +)
             :else (+ (abs (crossing-zero-partial -)) (crossing-zero-through-subtraction pos distance))
             )
           )
      )


(defn day01-part2
      [file-name]
      (let [movements (prepare-movements file-name)
            positions (reductions move 50 movements)]
           (->> (map crossing-zero-full-count positions movements)
                (reduce +)
           )
      ))