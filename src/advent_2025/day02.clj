(ns advent-2025.day02)
(require '[clojure.string :as str])

(defrecord IdRange [first-id last-id])

(defn- prepare-ranges
       [file-name]
       (map
         #(let [[a b] (str/split % #"-")] (->IdRange (bigint a) (bigint b)))
         (str/split (slurp file-name) #","))
       )

(defn find-palindromes-even-number-of-letters
      [{:keys [first-id last-id]}]
      (->> (range first-id (inc last-id))
           (map str)
           (filter #(even? (count %)))
           (filter #(let [len (count %)
                          first-half (subs % 0 (/ len 2))
                          second-half (subs % (/ len 2) len)]
                      (= first-half second-half)))
            (map bigint)
      )
      )

(defn day02-part1
      [file-name]
      (def final-vals (->> (prepare-ranges file-name)
                    (map find-palindromes-even-number-of-letters)
                    (filter not-empty)
                  ))
      (transduce cat + final-vals)
      )