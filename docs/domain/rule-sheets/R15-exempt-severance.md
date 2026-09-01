# R15 — Exempt severance pay

**Source:** art. 206 num. 4 E.T.

## Values

Exempt share by average monthly labor income of the last six months of the labor relationship.
The table does not vary by tax year; only the peso value of the UVT does.

| From UVT | To UVT | Exempt share |
|---|---|---|
| 0 | 350 | 100% |
| 350 | 410 | 90% |
| 410 | 470 | 80% |
| 470 | 530 | 60% |
| 530 | 590 | 40% |
| 590 | 650 | 20% |
| 650 | — | 0% |

## Formula

    average_uvt   = average_monthly_labor_income_last_6_months / uvt(tax_year)
    exempt_share  = lookup(average_uvt)
    exempt_amount = (severance + severance_interest) × exempt_share

## Conditions

- The average is taken over the last six months of the labor relationship, not over the
  calendar year.
- The exemption covers the severance payment and the interest on it alike.
- Inside the general 40% / 1.340 UVT cap (R7).
- An average of exactly 350 UVT is exempt in full: art. 206 num. 4 grants the whole exemption
  when the average *does not exceed* 350 UVT, and the 90% band starts above it. The same
  reading applies to every other boundary — the band a boundary belongs to is the one below it.
- **Open:** the engine adds severance paid, severance under the traditional regime and
  severance consigned to a fund, and applies one exempt share to the total. Whether the taxable
  moment differs between the traditional regime and the fund is not established here.

## Test case

**Given** severance and interest of $12.000.000 and an average monthly income of 300 UVT, tax
year 2025
**When** the exemption resolves → fully exempt, $12.000.000

**Given** the same severance and an average of 500 UVT
**When** the exemption resolves → the 470–530 band, 60%, `exempt_amount` = $7.200.000

**Given** the same severance and an average of exactly 350 UVT
**When** the exemption resolves → fully exempt at $12.000.000, not the $10.800.000 the 90% band
would give
