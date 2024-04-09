use std::fs;

use itertools::Itertools;

pub fn solution() {
    let raw: String = fs::read("./samples/station_balance.txt")
        .expect("file not found")
        .iter()
        .map(|b| *b as char)
        .collect();
    let lines: Vec<&str> = raw.lines().collect();

    let mut iter = lines.into_iter();

    let mut set = 1;
    while let Some(value) = iter.next() {
        let values = value.trim().split(" ").collect_vec();
        let chamber_count: usize = values[0].parse().unwrap();
        let mass_count: usize = values[1].parse().unwrap();
        if let Some(masses) = iter.next() {
            let mut masses: Vec<u32> = masses
                .trim()
                .split(" ")
                .map(|s| s.parse().unwrap())
                .collect_vec();
            masses.sort();

            let first_half = masses[mass_count - chamber_count..].to_vec();
            let mut second_half = masses[..mass_count - chamber_count].to_vec();
            second_half.reverse();
            while second_half.len() < first_half.len() {
                second_half.push(0);
            }
            let second_half = second_half;

            let chambers = first_half
                .into_iter()
                .zip(second_half.into_iter())
                .collect_vec();

            let weights = chambers.iter().map(|(f, s)| *f + *s).collect_vec();

            let len = chamber_count as f32;
            let sum = masses.clone().into_iter().map(|u| u as f32).sum::<f32>();
            let average = sum / len;

            let delta: f32 = weights.iter().map(|v| f32::abs(average - *v as f32)).sum();

            println!("Set #{}", set);
            for (i, (first, second)) in chambers.iter().enumerate() {
                let second = if *second == 0 {
                    "".to_owned()
                } else {
                    format!(" {}", second)
                };
                println!(" {}: {}{}", i, first, second)
            }
            println!("IMBALANCE = {:.5}\n", delta);
        } else {
            panic!();
        }
        set += 1;
    }
}
