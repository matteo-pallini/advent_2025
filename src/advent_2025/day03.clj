(ns advent-2025.day03)
(require '[clojure.math :as math])

(defn prepare-reversed-digits
      [value]
      (->> (map #(parse-long (str %)) value)
            (reverse)
            (vec)
            )
           )

(defn value-shift-decimal [value shifts] (* (math/pow 10 shifts) value))

(defn find-all-maxs
      [values count]
      (
        cond
        (= count 1) [(apply max values)]
        :else
        (let [count-n (dec count)
              [idx max-val] (apply max-key second (map-indexed vector (subvec values count-n)))
              max-val-adjusted-idx (+ idx count-n)
              values-post-max-removal (subvec values 0 max-val-adjusted-idx)
              ]
                                     (cons (value-shift-decimal max-val count-n) (find-all-maxs values-post-max-removal count-n))
             )
        )
      )


(defn find-largest
      ; we need to revert the digits as `max-key` will give us the last max it finds, but we need the first
      [count value]
      (let [nums (prepare-reversed-digits value)
            results (find-all-maxs nums count)]
           (bigint (reduce + results))
           )
      )



(defn main-day03
      [count file-name]
      (->> (slurp file-name)
           (clojure.string/split-lines)
           (map (partial find-largest count))
           (reduce +)
           )
      )

(def day03-part1 (partial main-day03 2))
(def day03-part2 (partial main-day03 12))