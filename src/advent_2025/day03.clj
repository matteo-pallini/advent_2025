(ns advent-2025.day03)

(defn prepare-reversed-digits
      [value]
      (->> (map #(parse-long (str %)) value)
            (reverse)
            (vec)
            )
           )


(defn find-largest
      ; we need to revert the digits as `max-key` will give us the last max it finds, but we need the first
      [value]
      (let [nums (prepare-reversed-digits value)
            [idx first-max]
            (apply max-key second
                   (map-indexed vector (subvec nums 1))) ; exclude the last digit as it can't be the first largest one
            second-max (apply max (subvec nums 0 (inc idx)))]
           (+ (* 10 first-max) second-max))
      )


(defn day03-part1
      [file-name]
      (->> (slurp file-name)
           (clojure.string/split-lines)
           (map find-largest)
           (reduce +)
           )
      )
