(ns advent-2025.day02)
(require '[clojure.string :as str])

(defrecord IdRange [first-id last-id])

(defn- prepare-ranges
       [file-name]
       (map
         #(let [[a b] (str/split % #"-")] (->IdRange (parse-long a) (parse-long b)))
         (str/split (slurp file-name) #","))
       )


(defn even-length-palindrome?
      [s]
      (let [n (count s)
            h (quot n 2)]
           (and (even? n)
                (= (subs s 0 h)
                   (subs s h n)))))


(defn find-palindromes-even-number-of-letters
      [{:keys [first-id last-id]}]
      (->> (range first-id (inc last-id))
           (map str)
           (filter even-length-palindrome?)
           (map bigint)
      )
      )

(defn find-invalid-ids
  [invalid-ids-identifier file-name]
  (->> (prepare-ranges file-name)
       (mapcat invalid-ids-identifier)
       (reduce +)
                       )
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