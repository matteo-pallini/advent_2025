(ns advent-2025.core-test
  (:require [clojure.test :refer :all]
            [advent-2025.core :refer :all]
            [advent-2025.day01 :refer [day01-part1 day01-part2]]
    ))

(deftest day01-part1-test
  (testing "day01 part1 works on sample input"
    (is (= (day01-part1 "data/day01/test_input.txt") 3))))

(deftest day01-part2-test
         (testing "day01 part2 works on sample input"
                  (is (= (day01-part2 "data/day01/test_input.txt") 6))))


(deftest day02-part1-test
         (testing "day02 part1 works on sample input"
                  (is (= (day01-part2 "data/day02/test_input.txt") 6))))
