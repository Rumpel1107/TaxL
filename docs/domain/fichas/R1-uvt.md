# R1 — UVT value per tax year

**Status:** drafted, pending professional review
**Primary source:** art. 868 E.T. · DIAN Res. 001264 de 2022 (AG2023), 000187 de 2023 (AG2024), 000193 de 2024 (AG2025)

## The rule

The tax code expresses almost every threshold, cap and bracket as a number of UVT rather than as
an amount in pesos, so that inflation does not require rewriting the law each year. The DIAN fixes
the peso value of one UVT before the first of January, based on the DANE consumer price index for
"ingresos medios" measured from October to October.

Two consequences the engine depends on:

- A threshold is stored as its number of UVT, never as pesos. The peso figure is derived.
- The UVT that applies is the one of the **año gravable being declared**, not the one in force on
  the day the return is filed.

Conversions to pesos are rounded to the nearest thousand (art. 868).

## Values

| Año gravable | UVT |
|---|---|
| 2023 | $42.412 |
| 2024 | $47.065 |
| 2025 | $49.799 |

## Worked example

The 25% exempt labor income is capped at 790 UVT (R4). For AG2025:

    790 × $49.799 = $39.341.210 → rounded → $39.341.000

The same cap for AG2024, with nothing changed but the year:

    790 × $47.065 = $37.181.350 → rounded → $37.181.000

## Test case

**Given** a cap expressed as 790 UVT
**When** the engine resolves it for año gravable 2025
**Then** it returns $39.341.000

**Given** the same cap of 790 UVT
**When** only the año gravable changes to 2024
**Then** it returns $37.181.000, with no other parameter edited

The second case is what metric M3 asserts: switching the tax year touches the parameter set and
nothing else.

## For the reviewer

Confirm that the rounding to the nearest thousand under art. 868 applies to the derived threshold,
and not only to the UVT value itself. Some publishers show the unrounded figure — for AG2025 the
1.400 UVT filing threshold appears both as $69.719.000 and as $69.718.600.
