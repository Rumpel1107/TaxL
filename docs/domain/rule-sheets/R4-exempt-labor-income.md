# R4 — 25% exempt labor income, cap 790 UVT

**Source:** art. 206 num. 10 E.T. (Ley 2277 de 2022 art. 2)

## Values

| Tax year | 790 UVT |
|---|---|
| 2023 | $33.505.000 |
| 2024 | $37.181.000 |
| 2025 | $39.341.000 |

## Formula

    base      = labor_payments − incrngo − deductions − afc_voluntary_pension − exempt_severance
    exempt_25 = min(0.25 × base, 790 × uvt(tax_year))

## Conditions

- The base is labor payments only, not the whole general schedule.
- The base is net: INCRNGO (R3), the schedule's deductions (R5, R6) and its other exempt
  income — AFC / voluntary pension (R6) and exempt severance pay (R15) — are all subtracted
  before the 25% is applied.
- The 72 UVT per dependent (art. 336 num. 3) and the 1% electronic-invoice deduction are not
  subtracted from the base; they fall outside the general cap (R7).
- The 790 UVT cap is annual, and applies after the 25%.
- The exemption is never negative: a negative net base yields zero, not a negative exemption.
- Inside the general 40% / 1.340 UVT cap (R7).
- A published worked example computes the 25% on labor payments net of INCRNGO alone, without
  subtracting deductions or other exempt income. It is wrong: the net base is what reproduces
  the reference returns.
- **Open:** art. 206 par. 5 extends this exemption to professional fees under conditions this
  sheet does not yet cover, and the MVP scope includes an employee with fees income opting for
  the benefit.

## Test case

**Given** labor payments of $100.000.000, INCRNGO of $8.000.000 and deductions of $12.000.000,
tax year 2025
**When** the exemption is resolved → base $80.000.000, 25% governs, `exempt_25` = $20.000.000

**Given** labor payments of $250.000.000, INCRNGO of $20.000.000 and deductions of $30.000.000,
tax year 2025
**When** the exemption is resolved → base $200.000.000, the 790 UVT cap governs, `exempt_25` =
$39.341.000

**Given** the first case plus $10.000.000 of AFC contributions
**When** the exemption is resolved → base $70.000.000 and `exempt_25` = $17.500.000, not
$20.000.000
