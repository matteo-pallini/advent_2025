(ns advent-2025.core
  (:require [advent-2025.day01 :refer [day01-part1, day01-part2]]))

(defn -main
  "I don't do a whole lot ... yet."
  []
  (println "Here my solutions:")
  (println "\tDay 1 part 1: Times position hit 0:" (day01-part1 "data/day01/input.txt"))
  (println "\tDay 1 part 2: Times position crossed 0:" (day01-part2 "data/day01/input.txt"))
      )