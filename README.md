# Hibernate Base Entity

A base class for Hibernate-managed JPA entities with proper 
implementations of `equals()` and `hashCode()`. Inspired by 
[Andrey Oganesyan](https://github.com/andreyoganesyan)'s and 
[Georgii Vlasov](https://github.com/honest-niceman)'s posts 
([1](https://habr.com/ru/companies/haulmont/articles/564682/), 
[2](https://tinyurl.com/4uaecbzy)) on side effects that Lombok's 
`@EqualsAndHashCode` annotation brings in when applied to such 
entities:
- HashSet and HashMap incorrectly work with the entities,
- Hibernate may load data for lazy fields where not intended.

The new superclass solves both of these problems.

# How to use

1. Copy the class to your project.
2. Inherit JPA entities from the class.
3. Ensure that the primary key in related tables has name `id` and 
type `UUID`.
