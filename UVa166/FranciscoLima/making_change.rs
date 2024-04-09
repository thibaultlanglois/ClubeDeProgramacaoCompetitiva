use std::{fs, str::FromStr};

use itertools::Itertools;

#[derive(Debug)]
struct Wallet(u8, u8, u8, u8, u8, u8, u16);

#[derive(Debug)]
struct ParseWalletError;

impl FromStr for Wallet {
    type Err = ParseWalletError;

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        let values = s.split(" ").collect_vec();
        if values.len() != 7 {
            return Err(ParseWalletError);
        }
        let c5 = values[0].parse().map_err(|_| ParseWalletError)?;
        let c10 = values[1].parse().map_err(|_| ParseWalletError)?;
        let c20 = values[2].parse().map_err(|_| ParseWalletError)?;
        let c50 = values[3].parse().map_err(|_| ParseWalletError)?;
        let d1 = values[4].parse().map_err(|_| ParseWalletError)?;
        let d2 = values[5].parse().map_err(|_| ParseWalletError)?;
        let price = values[6]
            .parse::<f32>()
            .map(|f| (f * 100.) as u16)
            .map_err(|_| ParseWalletError)?;

        Ok(Wallet(c5, c10, c20, c50, d1, d2, price))
    }
}

impl Wallet {
    pub fn pay(&self) -> Vec<(u16, u8)> {
        let Wallet(c5, c10, c20, c50, d1, d2, min) = *self;
        let max_value: u16 = (c5 as u16)
            + (c10 as u16) * 2
            + (c20 as u16) * 4
            + (c50 as u16) * 10
            + (d1 as u16) * 20
            + (d2 as u16) * 40;
        let mut res = vec![];
        for value in (min / 5)..=max_value {
            let mut sum = value * 5;
            let mut coin_count = 0;
            for (count, coin) in [(d2, 40), (d1, 20), (c50, 10), (c20, 4), (c10, 2), (c5, 1)] {
                let mut count = count;
                while sum >= coin * 5 && count > 0 {
                    coin_count += 1;
                    sum -= coin * 5;
                    count -= 1;
                }
            }

            if sum == 0 {
                res.push((value * 5, coin_count))
            }
        }
        res
    }
}

pub fn solution() {
    let raw: String = fs::read("./change_input.txt")
        .expect("file not found")
        .iter()
        .map(|b| *b as char)
        .collect();
    let mut lines: Vec<&str> = raw.lines().collect();
    lines.pop();
    let lines = lines;

    for line in lines {
        let wallet: Wallet = line.parse().expect("invalid values");
        // println!("{:?}", wallet);
        let possible_payments = wallet.pay();

        let mut min_amount = u8::MAX;
        for (value, mut count) in possible_payments {
            if wallet.6 > value {
                // println!("{}|{}", wallet.6, value);
                continue;
            }
            let mut change = value - wallet.6;
            for coin in [(200), (100), (50), (20), (10), (5)] {
                while change >= coin {
                    count += 1;
                    change -= coin;
                }
            }
            min_amount = min_amount.min(count);
        }
        println!("{}", min_amount)
    }
}
