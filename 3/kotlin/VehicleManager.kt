class VehicleManager {
    private val vehicles = mutableListOf<Vehicle>()
    private val owners = linkedMapOf<String, Owner>()

    fun findOwnerById(idNumber: String): Owner? = owners[idNumber]

    fun add(vehicle: Vehicle) {
        require(findByNumber(vehicle.number) == null) { "Duplicate vehicle number." }
        val owner = vehicle.owner
        val existing = owners[owner.idNumber]
        require(existing == null || existing.hasSameInformation(owner)) {
            "ID number already belongs to a different owner profile."
        }
        owners.putIfAbsent(owner.idNumber, owner)
        vehicles.add(vehicle)
    }

    fun findByNumber(number: String): Vehicle? {
        val normalized = Vehicle.normalizeNumber(number)
        return vehicles.find { it.number == normalized }
    }

    fun findByOwnerId(idNumber: String): List<Vehicle> = vehicles.filter { it.owner.idNumber == idNumber }

    fun deleteByManufacturer(manufacturer: Manufacturer): Int {
        val before = vehicles.size
        vehicles.removeAll { it.manufacturer == manufacturer }
        return before - vehicles.size
    }

    fun countByManufacturer(): Map<Manufacturer, Int> {
        val counts = Manufacturer.entries.associateWith { 0 }.toMutableMap()
        for (vehicle in vehicles) counts[vehicle.manufacturer] = counts.getValue(vehicle.manufacturer) + 1
        return counts
    }

    fun getMostCommonManufacturers(): List<Manufacturer> {
        val counts = countByManufacturer()
        val maximum = counts.values.maxOrNull() ?: 0
        return if (maximum == 0) emptyList() else counts.filterValues { it == maximum }.keys.toList()
    }

    fun sortByNumberDescending(): List<Vehicle> {
        vehicles.sortByDescending { it.number }
        return vehicles.toList()
    }

    fun countByType(): Map<String, Int> {
        val counts = linkedMapOf("Car" to 0, "Motorcycle" to 0, "Truck" to 0)
        for (vehicle in vehicles) counts[vehicle.type] = counts.getValue(vehicle.type) + 1
        return counts
    }
}
