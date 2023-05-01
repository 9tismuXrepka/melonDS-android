package me.magnum.melonds.impl.retroachievements

import me.magnum.melonds.database.daos.RAAchievementsDao
import me.magnum.melonds.database.entities.retroachievements.RAAchievementEntity
import me.magnum.melonds.database.entities.retroachievements.RAGameEntity
import me.magnum.melonds.database.entities.retroachievements.RAGameHashEntity
import me.magnum.melonds.database.entities.retroachievements.RAGameSetMetadata
import me.magnum.melonds.database.entities.retroachievements.RAPendingAchievementSubmissionEntity
import me.magnum.melonds.database.entities.retroachievements.RAUserAchievementEntity

/**
 * An [RAAchievementsDao] implementation that disables most functionality related to data caching that should not be stored to guarantee the best integration with the
 * RetroAchievements platform. This implementation allows the caching implementation to be maintained but not used. If in the future that implementation proves useful, this
 * DAO usage can be replaced with the actual DAO that interacts with the database.
 * The only data that is actually allowed to be cached is game information, which contains the rich presence patch. However, it should always be updated from the API and the
 * cached value should only be used while the session is ongoing.
 *
 * @param actualAchievementsDao The DAO that should be used for operations that are actually supported and actually stores and fetches the data
 */
class NoCacheRAAchievementsDao(private val actualAchievementsDao: RAAchievementsDao) : RAAchievementsDao() {

    override suspend fun getGameSetMetadata(gameId: Long): RAGameSetMetadata? {
        return null
    }

    override suspend fun updateGameSetMetadata(gameSetMetadata: RAGameSetMetadata) {
    }

    override suspend fun clearAllGameSetMetadataLastUserDataUpdate() {
    }

    override suspend fun getGameAchievements(gameId: Long): List<RAAchievementEntity> {
        return emptyList()
    }

    override suspend fun getAchievement(achievementId: Long): RAAchievementEntity? {
        return null
    }

    override suspend fun deleteGameAchievements(gameId: Long) {
    }

    override suspend fun insertGameAchievements(achievements: List<RAAchievementEntity>) {
    }

    override suspend fun updateGameData(gameData: RAGameEntity) {
    }

    override suspend fun updateGameData(gameId: Long, achievements: List<RAAchievementEntity>, richPresencePatch: String?) {
        actualAchievementsDao.updateGameData(gameId, emptyList(), richPresencePatch)
    }

    override suspend fun getGame(gameId: Long): RAGameEntity? {
        return actualAchievementsDao.getGame(gameId)
    }

    override suspend fun getGameUserUnlockedAchievements(gameId: Long, forHardcoreMode: Boolean): List<RAUserAchievementEntity> {
        return emptyList()
    }

    override suspend fun deleteGameUserUnlockedAchievements(gameId: Long) {
    }

    override suspend fun addUserAchievement(userAchievement: RAUserAchievementEntity) {
    }

    override suspend fun insertGameUserUnlockedAchievements(userAchievements: List<RAUserAchievementEntity>) {
    }

    override suspend fun deleteAllUserUnlockedAchievements() {
    }

    override suspend fun addPendingAchievementSubmission(pendingAchievementSubmission: RAPendingAchievementSubmissionEntity) {
    }

    override suspend fun getPendingAchievementSubmissions(): List<RAPendingAchievementSubmissionEntity> {
        return emptyList()
    }

    override suspend fun removePendingAchievementSubmission(pendingAchievementSubmission: RAPendingAchievementSubmissionEntity) {
    }

    override suspend fun deleteAllPendingAchievementSubmissions() {
    }

    override suspend fun deleteGameHashLibrary() {
        actualAchievementsDao.deleteGameHashLibrary()
    }

    override suspend fun insertGameHashLibrary(hashLibrary: List<RAGameHashEntity>) {
        actualAchievementsDao.insertGameHashLibrary(hashLibrary)
    }

    override suspend fun getGameHashEntity(gameHash: String): RAGameHashEntity? {
        return actualAchievementsDao.getGameHashEntity(gameHash)
    }
}