(ns advent-of-code-2022.day4
  (:require [advent-of-code-2022.core :as core]
            [clojure.string :as str]))

(defn parse-line
  [line]
  (let [[r1 r2] (str/split line #",")
        [r1-start r1-end] (str/split r1 #"-")
        [r2-start r2-end] (str/split r2 #"-")]
    [[(Integer/parseInt r1-start) (Integer/parseInt r1-end)]
     [(Integer/parseInt r2-start) (Integer/parseInt r2-end)]]))

(defn parse-input
  [input-file]
  (map parse-line (core/load-input input-file)))

(defn full-overlap?
  "return true if the first range fully overlaps the second range. A range is a seq of lower and upper bounds"
  [[r1-start r1-end] [r2-start r2-end]]
  (and (<= r1-start r2-start) (>= r1-end r2-end)))

(defn partial-overlap?
  "return true if the first range partially overlaps the second range. A range is a seq of lower and upper bounds"
  [[r1-start r1-end] [r2-start r2-end]]
  (or (<= r2-start r1-start r2-end) (<= r2-start r1-end r2-end)))

(defn overlap?
  "determine if either assignment in a pair overlaps the other"
  [[a1 a2] overlap-fn]
  (or (overlap-fn a1 a2) (overlap-fn a2 a1)))

(defn part1
  [input-file]
  (->> (parse-input input-file)
       (map #(overlap? % full-overlap?))
       (filter true?)
       count))

(defn part2
  [input-file]
  (->> (parse-input input-file)
       (map (fn [assignments] (or (overlap? assignments partial-overlap?) (overlap? assignments full-overlap?))))
       (filter true?)
       count))

