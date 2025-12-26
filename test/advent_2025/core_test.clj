(ns advent-2025.core-test
  (:require [clojure.test :refer :all]
            [advent-2025.core :refer :all]
            [advent-2025.day01 :refer [day01-part1 day01-part2]]
            [advent-2025.day02 :refer [day02-part1 day02-part2]]
            [advent-2025.day03 :refer [day03-part1 day03-part2]]
    ))

(deftest day01-part1-test
  (testing "day01 part1 works on sample input"
    (is (= (day01-part1 "data/day01/test_input.txt") 3))))

(deftest day01-part2-test
         (testing "day01 part2 works on sample input"
                  (is (= (day01-part2 "data/day01/test_input.txt") 6))))


(deftest day02-part1-test
         (testing "day02 part1 works on sample input"
                  (is (= (day02-part1 "data/day02/test_input.txt") 1227775554))))

(deftest day02-part2-test
         (testing "day02 part2 works on sample input"
                  (is (= (day02-part2 "data/day02/test_input.txt") 4174379265))))


(deftest day03-part1-test
         (testing "day03 part1 works on sample input"
                  (is (= (day03-part1 "data/day03/test_input.txt") 357))))


(deftest day03-part2-test
         (testing "day03 part2 works on sample input"
                  (is (= (day03-part2 "data/day03/test_input.txt") 3121910778619))))
