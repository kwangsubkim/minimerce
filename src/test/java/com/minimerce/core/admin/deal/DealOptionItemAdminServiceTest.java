package com.minimerce.core.admin.deal;

import com.minimerce.builder.DealOptionBuilder;
import com.minimerce.builder.DealOptionItemBuilder;
import com.minimerce.core.api.domain.deal.option.DealOption;
import com.minimerce.core.api.domain.deal.option.DealOptionRepository;
import com.minimerce.core.api.domain.deal.option.item.DealOptionItem;
import com.minimerce.core.api.domain.deal.option.item.DealOptionItemRepository;
import com.minimerce.core.api.support.util.Yn;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by gemini on 24/04/2017.
 */
public class DealOptionItemAdminServiceTest {
    private DealOptionItemAdminService optionItemService;
    private DealOptionRepository mockOptionRepository;
    private DealOptionItemRepository mockOptionItemRepository;

    @Before
    public void setUp() {
        mockOptionRepository = mock(DealOptionRepository.class);
        mockOptionItemRepository = mock(DealOptionItemRepository.class);
        optionItemService = new DealOptionItemAdminService(mockOptionRepository, mockOptionItemRepository);
    }

    @Test
    public void testShouldBeSave() {
        DealOption option = DealOptionBuilder.aDealOption().build();
        when(mockOptionRepository.findOne(1L)).thenReturn(option);

        DealOptionItem optionItem = DealOptionItemBuilder.aDealOptionItem().withId(null).build();
        optionItemService.save(1L, optionItem);
        verify(mockOptionRepository, times(1)).findOne(anyLong());
        verify(mockOptionItemRepository, times(1)).save(any(DealOptionItem.class));
    }

    @Test
    public void testShouldBeDeleteOptionItem() {
        DealOptionItem optionItem = DealOptionItemBuilder.aDealOptionItem().withDeleted(Yn.N).build();
        when(mockOptionItemRepository.findOne(1L)).thenReturn(optionItem);

        optionItemService.delete(1L);
        assertThat(optionItem.getDeleted(), is(Yn.Y));
    }
}