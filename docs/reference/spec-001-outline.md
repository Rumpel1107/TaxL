# Spec 001 free-tier flow — agreed outline

> **What goes in this document:** the structure agreed for `specs/001-free-tier-flow/spec.md`
> during the discovery sessions, before any spec file existed. Input material, not a spec — it is
> superseded the day the spec itself is written.
>
> Figures in examples are illustrative (D31).

## Sections

**1 · Purpose and scope.** Upload exógena → conservative estimate + filing deadline + obligation
check. Anti-scope: no full liquidation, no certificates yet, no profiles outside the base case.

**2 · Target user.** The segment of the reference case. Named as out of scope pending the M5
probe: (a) never filed before, (b) files with an accountant and wants to verify, (c) accepts the
DIAN suggested return blindly, (d) optimizer.

**3 · User flow.** Happy path: arrival → curated exógena download guide → upload → parse →
result (ceiling estimate + deadline by ID digits + the four thresholds evaluated) → tier X gate.

**4 · Edge cases and failure states.** Unreadable file (R11); out-of-scope profile → loud fail;
unrecognized data (D14); duplicates across reporting entities (D11); visibly incomplete exógena
(D12). Each states what the system detects, what the user sees, and what the user can do.

**5 · Acceptance criteria.** Given/When/Then. Example shapes: given an exógena where a trust
reports a mortgage disbursement, the estimate must not count it as income; given professional fees
in the
file, the system alerts and produces no figure.

**6 · Open questions to resolve before writing.** Does the conservative estimate include the 25%
exempt income — it moves the anchor by millions. What the flow is when the user turns out not to
be required to file. A single figure or a range.

## Related specs

- `002-exogena-parser` — R11–R14. Test material is the real exógena↔return pairs held locally.
- `003-calc-engine` — from the rule registry plus the golden tests.
