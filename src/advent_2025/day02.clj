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

(defn find-invalid-ids
  [invalid-ids-identifier file-name]
  (def final-vals (->> (prepare-ranges file-name)
                       (map invalid-ids-identifier)
                       (filter not-empty)
                       ))
  (transduce cat + final-vals)
  )

(def day02-part1 (partial find-invalid-ids find-palindromes-even-number-of-letters))

(defn repeated-candidate?
      [candidate remaining-digits]
      (let [candidate-len (count candidate)]
           (cond
             (empty? remaining-digits) true
             (< (count remaining-digits) candidate-len) false
             :else
               (let [next-to-match (subs remaining-digits 0 candidate-len)
                     remainings (subs remaining-digits candidate-len)]
                 (if (= candidate next-to-match)
                   (recur candidate remainings)
                   false)
                )
           )
        )
      )


(defn only-repeated-digits?
      [full-digit]
       (let [midpoint (quot (count full-digit) 2)]
            (->> (range 1 (+ 1 midpoint))
                 (map #(repeated-candidate? (subs full-digit 0 %) (subs full-digit %)))
                 (some true?)
                 )
        )
      )

(defn find-invalid-ids-repeated-digits
      [{:keys [first-id last-id]}]
      (->> (range first-id (inc last-id))
           (map str)
           (filter only-repeated-digits?)
           (map bigint)
           )
      )

(def day02-part2 (partial find-invalid-ids find-invalid-ids-repeated-digits))